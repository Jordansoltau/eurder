package com.example.eurder.domain.order;

import java.util.List;

public class Order {
    private final String orderId;
    private final List<ItemGroep> orderedItems;
    private final double price;
    private final String userId;


    public Order(String orderId, List<ItemGroep> orderedItems, double price, String userId) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.price = price;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
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
