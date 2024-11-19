package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception to fire a 403 (Unauthorized) HTTP status
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends BasicException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String reason) {
        super(reason);
    }
}
