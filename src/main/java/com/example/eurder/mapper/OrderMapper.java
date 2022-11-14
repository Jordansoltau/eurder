package com.example.eurder.mapper;

import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public List<OrderDTO> fromListOrderToListOrderDTO(List<Order> allOrders, String userId) {
        List<OrderDTO> orderDtoList = new ArrayList<>();
        orderDtoList.add(new OrderDTO((ArrayList<Order>) allOrders, userId));
        return orderDtoList;
    }

}
