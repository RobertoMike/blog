package com.example.demo.validations.annotations;

import com.example.demo.validations.ExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsConstrain {
    String message() default "The value not exist";
    String method() default "findById";
    Class<?> repository();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
