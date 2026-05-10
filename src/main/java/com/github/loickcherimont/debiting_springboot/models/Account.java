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
    private AmountEntity amountEntity;

    protected Account() {
    }

    public Account(String name, String owner, AmountEntity amountEntity) {
        this.name = name;
        this.owner = owner;
        this.amountEntity = amountEntity;
    }

    public Account(UUID id, String name, String owner, AmountEntity amountEntity) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.amountEntity = amountEntity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public AmountEntity getAmountEntity() {
        return amountEntity;
    }

    public void setAmountEntity(AmountEntity amountEntity) {
        this.amountEntity = amountEntity;
    }

}
