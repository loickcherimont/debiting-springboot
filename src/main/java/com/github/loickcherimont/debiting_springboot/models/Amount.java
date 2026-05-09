package com.github.loickcherimont.debiting_springboot.models;

import java.math.BigInteger;

import jakarta.persistence.Embeddable;

@Embeddable
public class Amount {
    private BigInteger value;
    private String currency;

    protected Amount() {
    }

    public Amount(BigInteger value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

}
