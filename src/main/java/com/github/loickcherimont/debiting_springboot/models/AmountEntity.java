package com.github.loickcherimont.debiting_springboot.models;

import java.math.BigInteger;

import jakarta.persistence.Embeddable;

@Embeddable
public class AmountEntity {
    private BigInteger amount;
    private String currency;

    protected AmountEntity() {
    }

    public AmountEntity(BigInteger amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(BigInteger amount) {
        this.amount = amount;
    }

}
