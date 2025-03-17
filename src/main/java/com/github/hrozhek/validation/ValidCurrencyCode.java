package com.github.hrozhek.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyCode {
    String message() default "Currency code must consist of 3 uppercase letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
