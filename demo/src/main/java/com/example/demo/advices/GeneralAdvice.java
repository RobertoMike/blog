package com.example.demo.advices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GeneralAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus()
    @ResponseBody
    ResponseEntity<String> exception(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
