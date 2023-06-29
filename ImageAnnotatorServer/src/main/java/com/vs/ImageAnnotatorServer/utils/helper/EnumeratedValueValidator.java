package com.vs.ImageAnnotatorServer.utils.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumeratedValueValidator implements ConstraintValidator<EnumeratedValue, String>, Logger {

    List<String> acceptedValues;

    @Override
    public void initialize(EnumeratedValue constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        logger.info("Custom enumvalidations enum : " + acceptedValues.toString());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        logger.info("Custom enumvalidations value : " + value);
        return acceptedValues.contains(value);
    }
}
