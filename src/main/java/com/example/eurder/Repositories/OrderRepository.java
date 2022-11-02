package com.example.eurder.Repositories;

import com.example.eurder.domain.order.ItemGroep;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class OrderRepository {

    private final Map<String, ArrayList<ItemGroep>> orderRepository;

    public OrderRepository() {
        this.orderRepository = hardCodedRepository();
    }

    public void saveCurrentOrder(String userId, ArrayList<ItemGroep> orders) {
        orderRepository.put(userId, orders);
    }

    private Map<String, ArrayList<ItemGroep>> hardCodedRepository(){
        Map<String, ArrayList<ItemGroep>> orderedList = new HashMap<>();
        ArrayList<ItemGroep> hardCodedList = new ArrayList<>();
        hardCodedList.add(new ItemGroep("10",1,LocalDate.now(),20));
        orderedList.put("15",hardCodedList);
        return orderedList;
    }
}
