package com.example.eurder.mapper;


import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {


    public OrderDTO mapFromOrderToOrderDto(Order order, List<ReservedOrder> reservedOrderList) {
        return new OrderDTO(order,reservedOrderList);
    }
}
