package com.example.eurder.domain.order;

import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Order {

    private final Map<String, ArrayList<ItemGroep>> copyOfOrderRepository;
    private final OrderRepository orderRepository;

    public Order(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.copyOfOrderRepository = new HashMap<>();

    }

    public void orderNewItem(String userId, ItemGroepDto itemGroepDto) {

        if (copyOfOrderRepository.get(userId) == null) {
            ArrayList<ItemGroep> newList = new ArrayList<>();
            ItemGroep itemGroep = new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase());
            newList.add(itemGroep);
            copyOfOrderRepository.put(userId, newList);
            orderRepository.saveCurrentOrder(userId, newList);
            return;
        }

        ArrayList<ItemGroep> updateList = copyOfOrderRepository.get(userId);
        ItemGroep itemGroep = new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase());
        updateList.add(itemGroep);
        copyOfOrderRepository.put(userId, updateList);
        orderRepository.saveCurrentOrder(userId, updateList);

    }

    public List<ItemGroep> getOrder(String id) {
        return copyOfOrderRepository.get(id);

    }
}
