package com.example.demo.advices;

import com.example.demo.exceptions.BasicException;
import com.example.demo.responses.errors.ErrorMessage;
import com.example.demo.responses.errors.ValidationErrorResponse;
import com.example.demo.responses.errors.Violation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// Annotation that specify to Spring that this class will manage exceptions
@ControllerAdvice
public class GeneralAdvice {
    // This annotation help us to define that exception we'll manage in this method
    @ExceptionHandler(Exception.class)
    // This allows us to return an object and Spring will transform it in the response
    @ResponseBody
    private ResponseEntity<ErrorMessage> exception(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(BasicException.class)
    @ResponseBody
    private ResponseEntity<ErrorMessage> exception(BasicException e) {
        System.out.println(e.getMessage());

        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    private ResponseEntity<ValidationErrorResponse> exception(ConstraintViolationException e) {
        System.out.println(e.getMessage());

        var error = new ValidationErrorResponse();
        e.getConstraintViolations().forEach(violation ->
            error.violations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage())
            )
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    private ResponseEntity<ValidationErrorResponse> exception(MethodArgumentNotValidException e) {
        var error = new ValidationErrorResponse();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
            error.violations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage())
            )
        );

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
