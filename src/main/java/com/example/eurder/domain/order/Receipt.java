package com.example.eurder.domain.order;

import java.util.List;

public class Receipt {
    private final List<ItemGroep> listOfOrder;
    private final double totalPrice;
    private final String userId;

    public Receipt(List<ItemGroep> listOfOrder, double totalPrice, String userId) {
        this.listOfOrder = listOfOrder;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public List<ItemGroep> getListOfOrder() {
        return listOfOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
