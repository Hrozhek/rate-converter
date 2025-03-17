package com.github.hrozhek.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Map;

@Schema(description = "Ответ с результатами конвертации валют")
public record CurrencyConversionResponse(

        @Schema(description = "Базовая валюта", example = "USD")
        String baseCurrency,

        @Schema(description = "Исходная сумма для конвертации", example = "100.50")
        BigDecimal amount,

        @Schema(description = "Результат конвертации: ключ - код валюты, значение - сконвертированная сумма",
                example = "{\"EUR\": 92.5, \"GBP\": 78.3}")
        Map<String, BigDecimal> convertedAmounts
) {}
