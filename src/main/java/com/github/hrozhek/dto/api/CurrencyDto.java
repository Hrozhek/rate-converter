package com.github.hrozhek.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrencyDto(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("min_size") String minSize
) {}
