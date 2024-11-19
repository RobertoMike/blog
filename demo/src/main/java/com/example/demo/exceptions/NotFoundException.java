package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception to fire a 404 (Not Found) HTTP status
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BasicException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(String reason) {
        super(reason);
    }
}
