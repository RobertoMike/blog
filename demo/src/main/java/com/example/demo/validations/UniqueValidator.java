package com.example.demo.validations;

import com.example.demo.validations.annotations.UniqueConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Optional;

public class UniqueValidator implements ConstraintValidator<UniqueConstraint, Object> {
    // This is a Spring class that allow you to get beans
    private final ApplicationContext applicationContext;
    private String method;
    private Class<?> repository;

    public UniqueValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(UniqueConstraint constraintAnnotation) {
        this.method = constraintAnnotation.method();
        this.repository = constraintAnnotation.repository();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // If the value is null we return true,
        // this because this validation is not responsible for checking nulls
        if (value == null) {
            return true;
        }

        try {
            // We get the beans of the repository
            Object instance = applicationContext.getBean(repository);
            // We search the method inside the repository
            Method callable = ClassUtils.getMethod(repository, method, null);
            // We invoke the method
            Object result = callable.invoke(instance, value);

            if (result instanceof Optional<?> el) {
                return el.isEmpty();
            }
            if (result instanceof Boolean exists) {
                return !exists;
            }
            return result == null;
        } catch (Exception e) {
            // Log the exception if we want or throw it
            throw new RuntimeException("Error inside UniqueValidator", e);
        }
    }
}
