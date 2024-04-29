package com.eazybytes.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	
	@NotEmpty(message = "Account number cannot be a empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message = "Account type cannot be a empty")
	private String accountType;
	
	@NotEmpty(message = "Branch address cannot be a empty")
	private String branchAddress;

}
