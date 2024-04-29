package com.eazybytes.account.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.AccountsDto;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.entity.Accounts;
import com.eazybytes.account.entity.Customer;
import com.eazybytes.account.exception.CustomerAlreadyExsitsException;
import com.eazybytes.account.exception.ResourceNotFoundException;
import com.eazybytes.account.mapper.AccountsMapper;
import com.eazybytes.account.mapper.CustomerMapper;
import com.eazybytes.account.repository.AccountsRepository;
import com.eazybytes.account.repository.CustomerRepository;
import com.eazybytes.account.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl extends RuntimeException implements IAccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccounts(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExsitsException(
					"Customer already registered with given mobile number " + customerDto.getMobileNumber());
		}
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Anonymous");
		Customer savCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savCustomer));

	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccounts = new Accounts();
		newAccounts.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccounts.setAccountNumber(randomAccNumber);
		newAccounts.setAccountType(AccountsConstants.SAVINGS);
		newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
		newAccounts.setCreatedAt(LocalDateTime.now());
		newAccounts.setCreatedBy("Anonymous");
		return newAccounts;
	}

	@Override
	public Optional<CustomerDto> fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		return Optional.ofNullable(customerDto);
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (!accountsDto.equals(null)) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));
			CustomerMapper.mapCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
