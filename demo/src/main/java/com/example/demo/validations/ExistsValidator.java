package com.example.demo.validations;

import com.example.demo.validations.annotations.ExistsConstrain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Optional;

public class ExistsValidator implements ConstraintValidator<ExistsConstrain, Long> {
    private final ApplicationContext applicationContext;
    private String method;
    private Class<?> repository;

    public ExistsValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(ExistsConstrain constraintAnnotation) {
        this.method = constraintAnnotation.method();
        this.repository = constraintAnnotation.repository();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        try {
            Object instance = applicationContext.getBean(repository);
            Method callable = ClassUtils.getMethod(repository, method, null);
            Object result = callable.invoke(instance, value);

            if (result instanceof Optional<?> el) {
                return el.isPresent();
            }
            if (result instanceof Boolean exists) {
                return exists;
            }

            return result != null;
        } catch (Exception e) {
            throw new RuntimeException("Error inside ExistsValidator", e);
        }
    }
}
