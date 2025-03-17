package com.github.hrozhek.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, Set<String>> {

    private static final String CURRENCY_REGEX = "^[A-Z]{3}$";

    @Override
    public boolean isValid(Set<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.stream().allMatch(currency -> currency.matches(CURRENCY_REGEX));
    }
}
