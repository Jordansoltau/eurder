package com.example.eurder.domain.item;

import java.util.UUID;

public class Item {
    private final String id;
    private final String description;
    private final String name;
    private final double price;
    private int amount;

    public Item(String id, String name, String description, double price, int amount) {
//        this.id = UUID.randomUUID().toString();
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void decreaseAmount(int amountToPurchase) {
        if (amount < amountToPurchase) {
            throw new IllegalArgumentException("There is not enough" + getName() + "in  stock. Maximum amount to purchase = " + getAmount());
        }
        this.amount -= amountToPurchase;
    }
}
