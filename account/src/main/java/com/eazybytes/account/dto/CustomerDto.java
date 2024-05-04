package com.eazybytes.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account Details")
public class CustomerDto {

	@NotEmpty(message = "Name cannot be a empty")
	@Size(max = 30, min = 5, message = "The length of the name should be between 5 to 30 character")
	@Schema(description = "Name of the Customer",example = "Raj Patil")
	private String name;

	@NotEmpty(message = "Email cannot be a empty")
	@Email(message = "Email should be valid email address")
	@Schema(description = "Email of the Customer",example = "rajp@yopmail.com")
	private String email;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	@Schema(description = "Mobile number of the Customer",example = "1234567890")
	private String mobileNumber;

	private AccountsDto accountsDto;

}
