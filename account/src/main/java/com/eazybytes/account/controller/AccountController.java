package com.eazybytes.account.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.account.constants.AccountsConstants;
import com.eazybytes.account.dto.AccountsContactInfoDto;
import com.eazybytes.account.dto.CustomerDto;
import com.eazybytes.account.dto.ErrorResponseDto;
import com.eazybytes.account.dto.ResponseDto;
import com.eazybytes.account.exception.ResourceNotFoundException;
import com.eazybytes.account.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST API's for Accounts", description = "CRUD apis for create,update,fetch and delete Account Detail's")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {

	private IAccountsService iAccountService;
	
	@Value("${account.name)")
	private String name;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;

	@Operation(description = "Rest Api to create new customer and account inside DataBase", summary = "Creat Account REST API")
	@ApiResponse(responseCode = "201", description = "Http Status Created")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountService.createAccounts(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(description = "Rest Api to fetch customer detail from DataBase", summary = "Fetch Account Details REST API")
	@ApiResponse(responseCode = "200", description = "Http Status OK")
	@GetMapping("/fetch")
	public ResponseEntity<Optional<CustomerDto>> fetchAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		Optional<CustomerDto> customerDto = iAccountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@Operation(description = "Rest Api to update customer detail in DataBase", summary = "Update Account Details REST API")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "417", description = "Exception Failed") })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = iAccountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(description = "Rest Api to delete customer detail from DataBase", summary = "Delete Account Details REST API")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "417", description = "Exception Failed") })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}

	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
	}

	@GetMapping("/account-name")
	public ResponseEntity<String> getAccountName() {
		return ResponseEntity.status(HttpStatus.OK).body(name);
	}
	
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_VERSION"));
	}

}
