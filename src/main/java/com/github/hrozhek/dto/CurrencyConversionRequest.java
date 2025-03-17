package com.github.hrozhek.dto;

import com.github.hrozhek.validation.ValidCurrencyCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

@Schema(description = "Запрос на конвертацию валют")
public record CurrencyConversionRequest(

        @NotBlank(message = "Base currency cannot be empty")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Base currency must consist of 3 uppercase letters")
        @Schema(description = "Базовая валюта в формате ISO 4217 (3 заглавные буквы)", example = "USD")
        String baseCurrency,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        @Schema(description = "Сумма для конвертации", example = "100.50")
        BigDecimal amount,

        @NotEmpty(message = "At least one target currency must be provided")
        @ValidCurrencyCode
        @Schema(description = "Список целевых валют для конвертации в формате ISO 4217", example = "[\"EUR\", \"GBP\"]")
        Set<String> targetCurrencies
) {}
