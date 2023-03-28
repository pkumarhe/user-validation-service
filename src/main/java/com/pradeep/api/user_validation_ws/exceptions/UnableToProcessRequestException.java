package com.pradeep.api.user_validation_ws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnableToProcessRequestException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UnableToProcessRequestException(String message) {
		super(message);
	}
}