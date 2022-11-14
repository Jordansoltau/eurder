package com.example.eurder.domain.order;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;
    @Embedded
    private ItemGroep orderedItems;
    @Column(name = "priceoforder")
    private double totalPrice;


    public Order() {

    }

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
