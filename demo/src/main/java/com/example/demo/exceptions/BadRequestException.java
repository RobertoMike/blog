package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BasicException {
	@Serial
	private static final long serialVersionUID = 1L;

	public BadRequestException(String reason) {
		super(reason);
	}
	public BadRequestException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
}
