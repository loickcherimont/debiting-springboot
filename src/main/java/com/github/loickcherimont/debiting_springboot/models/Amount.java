package com.github.loickcherimont.debiting_springboot.models;

import java.math.BigInteger;
import java.util.UUID;

public class Amount {
    private UUID id;
    private BigInteger value;
    private String currency;
    
    public Amount(UUID id, BigInteger value, String currency) {
        this.id = id;
        this.value = value;
        this.currency = currency;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
