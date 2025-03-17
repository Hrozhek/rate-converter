package com.github.hrozhek.service;

import com.github.hrozhek.dto.CurrencyConversionRequest;
import com.github.hrozhek.dto.CurrencyConversionResponse;
import com.github.hrozhek.dto.api.ExchangeResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateMappingService {

    public CurrencyConversionResponse convertToTargetCurrencies(CurrencyConversionRequest request, ExchangeResponseDto exchangeRateResponse) {
        Map<String, BigDecimal> convertedAmounts = new HashMap<>();

        for (var targetCurrency : request.targetCurrencies()) {
            var rate = exchangeRateResponse.rates().get(targetCurrency);
            var convertedAmount = request.amount().multiply(rate).setScale(2, RoundingMode.FLOOR);
            convertedAmounts.put(targetCurrency, convertedAmount);
        }
        return new CurrencyConversionResponse(request.baseCurrency(), request.amount(), convertedAmounts);
    }
}
