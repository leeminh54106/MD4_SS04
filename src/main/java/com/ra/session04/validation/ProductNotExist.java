package com.ra.session04.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = ProductValidateConstraint.class
)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface ProductNotExist {
    String message() default "Product Name is exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
