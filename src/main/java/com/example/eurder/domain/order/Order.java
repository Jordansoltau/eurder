package com.example.eurder.domain.order;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class Order {

    private Map<String, ArrayList<ItemGroep>> orderedItems;
    private double totalPrice;



    public Order() {
        orderedItems = new HashMap<>();
        this.totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Map<String, ArrayList<ItemGroep>> getOrderedItems() {
        return orderedItems;
    }

    public Map<String, ArrayList<ItemGroep>> addOrder(String userId, ItemGroep itemGroep) {
        if (orderedItems.isEmpty()) {
            return createAnOrder(userId, itemGroep);
        } else {
            return addOrderInList(userId, itemGroep);
        }
    }

    private Map<String, ArrayList<ItemGroep>> addOrderInList(String userId, ItemGroep itemGroep) {
        ArrayList<ItemGroep> updateOrderList = orderedItems.get(userId);
        updateOrderList.add(itemGroep);
        orderedItems.put(userId, updateOrderList);
        updateTotalPrice(userId);
        return orderedItems;
    }


    private Map<String, ArrayList<ItemGroep>> createAnOrder(String userId, ItemGroep itemGroep) {
        ArrayList<ItemGroep> order = new ArrayList<>();
        order.add(itemGroep);
        orderedItems.put(userId, order);
        updateTotalPrice(userId);
        return orderedItems;
    }

    private void updateTotalPrice(String userId) {
        this.totalPrice = calculatePriceForEachOrder(userId);
    }

    private double calculatePriceForEachOrder(String userId) {
        ArrayList<ItemGroep> calculateList = orderedItems.get(userId);
        return calculateList.stream().mapToDouble(ItemGroep::getPriceOfOrder).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
