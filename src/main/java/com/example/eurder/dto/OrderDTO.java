package com.example.eurder.dto;

import com.example.eurder.domain.order.Order;

import java.util.ArrayList;
import java.util.Map;

public class OrderDTO {

    private final  ArrayList<Order> order;
    private final double price;

    public OrderDTO(ArrayList<Order> order) {
        this.order = order;
        this.price = calculateTotalPrice(order);
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
