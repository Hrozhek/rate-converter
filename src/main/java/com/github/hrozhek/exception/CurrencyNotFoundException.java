package com.github.hrozhek.exception;

import java.util.Collection;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currency) {
        super("Currency %s not found".formatted(currency));
    }

    public CurrencyNotFoundException(Collection<String> currencies) {
        super("Currencies %s not found".formatted(currencies));
    }
}
