package com.example.eurder.mapper;


import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.service.dto.ReservedOrderDTO;
import com.example.eurder.service.dto.orderDto.ItemGroepClientViewDTO;
import com.example.eurder.service.dto.orderDto.OrderDTO;
import com.example.eurder.service.dto.personDto.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public List<ReservedOrderDTO>  mapFromReservedOrderToReservedOrderDtoList(List<ReservedOrder> listToMap) {
        List<ReservedOrderDTO> listToReturn = new ArrayList<>();
        for (ReservedOrder reservedOrder:listToMap){
            ReservedOrderDTO reservedOrderDTO = new ReservedOrderDTO(reservedOrder.getItemGroep().getItem().getId(),
                    reservedOrder.getItemGroep().getItem().getName(),
                    reservedOrder.getItemGroep().getAmount(),
                    reservedOrder.getItemGroep().getShippingdate(),
                    reservedOrder.getItemGroep().getPriceOfOrder());
            listToReturn.add(reservedOrderDTO);
        }
        return listToReturn;
    }
}
