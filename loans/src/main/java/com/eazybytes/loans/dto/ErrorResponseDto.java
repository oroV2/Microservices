package com.eazybytes.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Schema hold the data of Error Response", name = "Error Response")
public class ErrorResponseDto {

	@Schema(description = "Api path invoke by client")
	private String apiPath;

	@Schema(description = "Error code respresent the error invoke")
	private HttpStatus errorCode;

	@Schema(description = "Error message respresent the error happened")
	private String errorMsg;

	@Schema(description = "Error time respresent the error time")
	private LocalDateTime errorTime;

}
