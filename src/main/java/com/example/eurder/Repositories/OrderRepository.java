package com.example.eurder.Repositories;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class OrderRepository {

    private final Map<String, ArrayList<ItemGroep>> orderRepository;

    public OrderRepository() {
        this.orderRepository = new HashMap<>();

    }

    public void saveCurrentOrder(String userId, ArrayList<ItemGroep> orders) {
        orderRepository.put(userId, orders);
    }
}
