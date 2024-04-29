package com.eazybytes.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
	
	@NotEmpty(message = "Name cannot be a empty")
	@Size(max = 30,min = 5,message = "The length of the name should be between 5 to 30 character")
	private String name;
	
	@NotEmpty(message = "Email cannot be a empty")
	@Email(message = "Email should be valid email address")
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	private AccountsDto accountsDto;

}
