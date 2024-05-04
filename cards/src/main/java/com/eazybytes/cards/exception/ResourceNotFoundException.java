package com.eazybytes.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6348483356371942298L;

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldvalue) {

		super(String.format("%s not found with the given input data %s : %s", resourceName, fieldName, fieldvalue));
	}

}
