package com.example.demo.validations.annotations;

import com.example.demo.validations.UniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraint {
    String message() default "The value already exists";
    String method();
    Class<?> repository();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
