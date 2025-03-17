package com.github.hrozhek.dto;

import java.math.BigDecimal;
import java.util.Map;

public record CurrencyConversionResponse(String baseCurrency, BigDecimal amount, Map<String, BigDecimal> convertedAmounts) {}
