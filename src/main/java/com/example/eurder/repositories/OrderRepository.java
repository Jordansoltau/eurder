package com.example.eurder.repositories;

import com.example.eurder.domain.order.ItemGroep;

import com.example.eurder.domain.order.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class OrderRepository {

    private final Map<String, Order> orderRepository;

    public OrderRepository(Map<String, Order> orderRepository) {
        this.orderRepository = orderRepository;
        hardCodedRepository();
    }


    private void hardCodedRepository() {
        Order orderHardCoded = new Order();
        orderHardCoded.addOrder("1",new ItemGroep("10", 1, LocalDate.now(), 20));
        orderRepository.put("15",orderHardCoded);
    }


    public void addOrder(String userId, Order order) {
            orderRepository.put(userId, order);
    }

    public Map<String, Order> getAllOrders() {
        return orderRepository;
    }
}
