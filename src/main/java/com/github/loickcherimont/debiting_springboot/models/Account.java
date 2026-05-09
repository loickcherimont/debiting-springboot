package com.github.loickcherimont.debiting_springboot.models;

import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String owner;

    @Embedded
    private Amount amount;

    protected Account() {}

    public Account(String name, String owner, Amount amount) {
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
