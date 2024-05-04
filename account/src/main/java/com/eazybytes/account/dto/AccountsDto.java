package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Schema to hold Customer's Account Details")
public class AccountsDto {
	
	@NotEmpty(message = "Account number cannot be a empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
	@Schema(description = "Customer bank account number",example = "1234567890")
	private Long accountNumber;
	
	@NotEmpty(message = "Account type cannot be a empty")
	@Schema(description = "Customer bank account type",example = "Saving")
	private String accountType;
	
	@NotEmpty(message = "Branch address cannot be a empty")
	@Schema(description = "Bank branch address",example = "123 Main Street, New York")
	private String branchAddress;

}
