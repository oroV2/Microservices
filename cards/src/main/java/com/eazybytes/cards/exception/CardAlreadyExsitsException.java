package com.eazybytes.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExsitsException extends RuntimeException {

	public CardAlreadyExsitsException(String message) {
		super(message);
	}

}
