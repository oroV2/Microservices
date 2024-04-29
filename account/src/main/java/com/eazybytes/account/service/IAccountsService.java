package com.eazybytes.account.service;

import java.util.Optional;

import com.eazybytes.account.dto.CustomerDto;

public interface IAccountsService {

	void createAccounts(CustomerDto customerDto);

	Optional<CustomerDto> fetchAccount(String mobileNumber);

	boolean updateAccount(CustomerDto customerDto);
	
	boolean deleteAccount(String mobileNumber);
	
}
