package com.example.eurder.domain.order;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ItemGroep> orderedItems;
    private final double price;
    private final String userId;


    public Receipt(List<ItemGroep> orderedItems, double price, String userId) {
        this.orderedItems = orderedItems;
        this.price = price;
        this.userId = userId;
    }

    public List<ItemGroep> getOrderedItems() {
        return orderedItems;
    }

    public double getPrice() {
        return price;
    }

    public String getUserId() {
        return userId;
    }
}
