package com.example.eurder.dto;

import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDTO {

    private final Map<Order, List<ReservedOrder>> order;


    public OrderDTO(Map<Order, List<ReservedOrder>> order) {
        this.order = order;
    }

    public OrderDTO(Order order, List<ReservedOrder> reservedOrderList) {
        this.order = setupOrderDto(order,reservedOrderList);
    }

    private Map<Order, List<ReservedOrder>> setupOrderDto(Order order, List<ReservedOrder> reservedOrderList) {
        HashMap<Order,List<ReservedOrder>> orderArrayListHashMap = new HashMap<>();
        orderArrayListHashMap.put(order, reservedOrderList);
        return orderArrayListHashMap;
    }

    public Map<Order, List<ReservedOrder>> getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order=" + order +
                '}';
    }
}
