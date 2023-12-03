package org.acme.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private List<String> acceptedTypes;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        this.acceptedTypes = Arrays.stream(constraintAnnotation
                .enumClass()
                .getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return acceptedTypes.contains(value);
    }
}
