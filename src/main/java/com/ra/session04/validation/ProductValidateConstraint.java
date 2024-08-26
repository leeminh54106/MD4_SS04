package com.ra.session04.validation;

import com.ra.session04.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidateConstraint implements ConstraintValidator<ProductNotExist, String> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !productRepository.existsByName(s);
    }
}
