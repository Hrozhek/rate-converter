package com.github.hrozhek.controller;

import com.github.hrozhek.dto.CurrencyConversionRequest;
import com.github.hrozhek.dto.CurrencyConversionResponse;
import com.github.hrozhek.service.ExchangeRateServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/currency")
@Tag(name = "Конвертация валют", description = "API для работы с курсами валют")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final ExchangeRateServiceFacade exchangeRateServiceFacade;

    @PostMapping("/convert")
    @Operation(summary = "Конвертировать валюту", description = "Конвертирует сумму из одной валюты в несколько целевых")
    public CurrencyConversionResponse convertCurrency(@Valid @RequestBody CurrencyConversionRequest request) {
        return exchangeRateServiceFacade.exchange(request);
    }
}
