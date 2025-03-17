package com.github.hrozhek.service;

import com.github.hrozhek.dto.CurrencyConversionRequest;
import com.github.hrozhek.dto.CurrencyConversionResponse;
import com.github.hrozhek.dto.api.ExchangeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceFacade {

    private final ExchangeRateMappingService exchangeRateMappingService;
    private final ExchangeRateRequestService exchangeRateRequestService;

    public CurrencyConversionResponse exchange(CurrencyConversionRequest request) {
        exchangeRateRequestService.checkCurrencies(request.baseCurrency(), request.targetCurrencies());

        ExchangeResponseDto exchangeRateResponse = exchangeRateRequestService.getExchangeRates(request.baseCurrency());
        return exchangeRateMappingService.convertToTargetCurrencies(request, exchangeRateResponse);
    }
}
