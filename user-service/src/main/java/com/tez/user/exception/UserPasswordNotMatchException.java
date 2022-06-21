package com.tez.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class UserPasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException(String message) {
		super(message);
	}

}
