package com.github.hrozhek.service;

import com.github.hrozhek.dto.api.ApiResponse;
import com.github.hrozhek.dto.api.CurrencyDto;
import com.github.hrozhek.dto.api.ExchangeResponseDto;
import com.github.hrozhek.exception.CurrencyNotFoundException;
import com.github.hrozhek.exception.ServiceResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateRequestService {

    private final RestTemplate restTemplate;

    @Value("${exchange.api.url.currencies}")
    private String getCurrenciesUrl;

    @Value("${exchange.api.url.rates-template}")
    private String getRateUrlTemplate;

    public void checkCurrencies(String baseCurrency, Set<String> targetCurrencies) {
        ApiResponse<List<CurrencyDto>> response;
        try {
            response = restTemplate.exchange(
                    getCurrenciesUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ApiResponse<List<CurrencyDto>>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceResponseException("Cannot get response from %s".formatted(getCurrenciesUrl));
        }
        if (response == null || CollectionUtils.isEmpty(response.data())) {
            throw new ServiceResponseException("Response from %s is empty".formatted(getCurrenciesUrl));
        }
        Set<String> availableCurrencies = response.data().stream()
                .map(CurrencyDto::id)
                .collect(Collectors.toSet());
        if (!availableCurrencies.contains(baseCurrency)) {
            throw new CurrencyNotFoundException(baseCurrency);
        }
        if (!availableCurrencies.containsAll(targetCurrencies)) {
            var absentCurrencies = new HashSet<>(targetCurrencies);
            absentCurrencies.removeAll(availableCurrencies);
            throw new CurrencyNotFoundException(absentCurrencies);
        }
    }

    public ExchangeResponseDto getExchangeRates(String baseCurrency) {
        String url = getRateUrlTemplate.formatted(baseCurrency);
        try {
            var responseBody = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ApiResponse<ExchangeResponseDto>>() {}
            ).getBody();
            return responseBody.data();
        } catch (NullPointerException npe) {
            throw new ServiceResponseException("Response from %s is empty".formatted(getRateUrlTemplate));
        } catch (Exception e) {
            throw new ServiceResponseException("Cannot get response from %s".formatted(getRateUrlTemplate));
        }
    }

}
