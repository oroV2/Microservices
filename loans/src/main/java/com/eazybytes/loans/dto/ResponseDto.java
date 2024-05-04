package com.eazybytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold Response Status")
public class ResponseDto {

	@Schema(description = "Rsponse status code")
	private String statusCode;

	@Schema(description = "Response status message")
	private String statusMsg;

}
