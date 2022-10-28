package com.example.eurder.Repositories;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderRepository {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final Map<String, ItemGroep> orderRepository;
    private final String orderId;

    public OrderRepository(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = new HashMap<>();
        this.orderId = randomUniqueIdGenerator();
    }


    private String randomUniqueIdGenerator(){
        String randomId = UUID.randomUUID().toString();
        while(orderRepository.get(randomId)!=null){
            randomId = UUID.randomUUID().toString();
        }
        return randomId;
    }

    public void addNewOrder(ItemGroep itemGroep) {
        orderRepository.put(randomUniqueIdGenerator(),itemGroep);
    }
}
