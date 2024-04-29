package com.eazybytes.account.mapper;

import com.eazybytes.account.dto.AccountsDto;
import com.eazybytes.account.entity.Accounts;

public class AccountsMapper {
	
	public static AccountsDto mapToAccountsDto (Accounts accounts, AccountsDto accountsDto) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}
	
	public static Accounts mapAccounts (AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}

}
