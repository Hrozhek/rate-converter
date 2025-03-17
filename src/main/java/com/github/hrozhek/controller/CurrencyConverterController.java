package com.github.hrozhek.controller;

import com.github.hrozhek.dto.CurrencyConversionRequest;
import com.github.hrozhek.dto.CurrencyConversionResponse;
import com.github.hrozhek.service.ExchangeRateServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final ExchangeRateServiceFacade exchangeRateServiceFacade;

    @PostMapping("/convert")
    public CurrencyConversionResponse convertCurrency(@Valid @RequestBody CurrencyConversionRequest request) {
        return exchangeRateServiceFacade.exchange(request);
    }
}
