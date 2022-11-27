package com.example.eurder.dto;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Person;

import java.util.List;

public class OrderDTO {

    private final Integer orderId;
    private final List<ItemGroepClientViewDTO> reservedOrderList;
    private final PersonDTO user;
    private final double totalPrice;

    public OrderDTO(Integer orderId, List<ItemGroepClientViewDTO> reservedOrderList, PersonDTO userId, double totalPrice) {
        this.orderId = orderId;
        this.reservedOrderList = reservedOrderList;
        this.user = userId;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public List<ItemGroepClientViewDTO> getReservedOrderList() {
        return reservedOrderList;
    }

    public PersonDTO getUserId() {
        return user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", reservedOrderList=" + reservedOrderList +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
