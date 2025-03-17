package com.github.hrozhek.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeResponseDto(
        @JsonProperty("currency") String currency,
        @JsonProperty("rates") Map<String, BigDecimal> rates
) {}
