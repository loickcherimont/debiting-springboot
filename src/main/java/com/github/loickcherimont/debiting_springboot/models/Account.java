package com.github.loickcherimont.debiting_springboot.models;

import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
    private String name;
    private String owner;
    private Amount amount;

    public Account(UUID id, String name, String owner, Amount amount) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

}
