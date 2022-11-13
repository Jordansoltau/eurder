package com.example.eurder.dto;

import com.example.eurder.domain.order.Order;

import java.util.ArrayList;

public class OrderDTO {

    private final  ArrayList<Order> order;
    private final double price;

    private final String userId;


    public OrderDTO(ArrayList<Order> order, String userId) {
        this.order = order;
        this.price = calculateTotalPrice(order);
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    private double calculateTotalPrice(ArrayList<Order> order) {
        double sum = 0;
        for (Order ordered : order){
            sum+=ordered.getTotalPrice();
        }
        return sum;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public double getPrice() {
        return price;
    }
}
