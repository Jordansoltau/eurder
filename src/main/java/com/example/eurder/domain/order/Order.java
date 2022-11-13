package com.example.eurder.domain.order;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Order {

    private final ItemGroep orderedItems;
    private final double totalPrice;


    public Order(ItemGroep orderedItems) {
        this.orderedItems = orderedItems;
        this.totalPrice = calculatePriceOfOrder(orderedItems);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ItemGroep getOrderedItems() {
        return orderedItems;
    }

    private double calculatePriceOfOrder(ItemGroep itemGroep) {
        return itemGroep.getPriceOfOrder();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
