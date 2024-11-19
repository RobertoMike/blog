package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BasicException extends RuntimeException {
    public BasicException(String reason) {
        super(reason);
    }

    public BasicException(String reason, Throwable throwable) {
        super(reason, throwable);
    }

    public BasicException(Throwable throwable) {
        super(throwable);
    }

    public HttpStatus getStatus() {
        var annotation = getClass().getAnnotation(ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
