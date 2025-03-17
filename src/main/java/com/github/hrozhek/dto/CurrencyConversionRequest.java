package com.github.hrozhek.dto;

import com.github.hrozhek.validation.ValidCurrencyCode;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record CurrencyConversionRequest(
        @NotBlank(message = "Base currency cannot be empty")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Base currency must consist of 3 uppercase letters")
        String baseCurrency,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotEmpty(message = "At least one target currency must be provided")
        @ValidCurrencyCode
        Set<String> targetCurrencies
) {}
