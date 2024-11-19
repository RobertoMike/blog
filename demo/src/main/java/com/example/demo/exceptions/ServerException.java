package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception to fire a 404 (Not Found) HTTP status
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends BasicException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ServerException(String reason) {
        super(reason);
    }

    public ServerException(String message, Exception reason) {
        super(message, reason);
    }

    public ServerException(Throwable throwable) {
        super(throwable);
    }
}
