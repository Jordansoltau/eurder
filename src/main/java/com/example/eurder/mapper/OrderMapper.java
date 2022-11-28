package com.example.eurder.mapper;


import com.example.eurder.domain.order.Order;
import com.example.eurder.service.dto.orderDto.ItemGroepClientViewDTO;
import com.example.eurder.service.dto.orderDto.OrderDTO;
import com.example.eurder.service.dto.personDto.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderDTO mapFromOrderToOrderDto(Order order, List<ItemGroepClientViewDTO> reservedOrderList) {
        return new OrderDTO(order.getId()
                ,reservedOrderList
                ,new PersonDTO(order.getUser().getUserId()
                                ,order.getUser().getFullName())
                ,order.getTotalPrice());
    }
}
