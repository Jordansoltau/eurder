package com.example.eurder.service;

import com.example.eurder.Repositories.ReservedOrderRepository;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservedOrderService {
    private final ReservedOrderRepository reservedOrderRepository;

    public ReservedOrderService(ReservedOrderRepository reservedOrderRepository) {
        this.reservedOrderRepository = reservedOrderRepository;
    }
    public double getTotalprice(Integer userId) {
        List<ReservedOrder> listOfAllReservedOrdersOfUserNotConfirmed = reservedOrderRepository.findReservedOrderByPerson_IdWhereOrder_IDIsNull(userId);
        double totalprice = 0;
        for (ReservedOrder reservedOrder:listOfAllReservedOrdersOfUserNotConfirmed){
            totalprice+= reservedOrder.getItemGroep().getPriceOfOrder();
        }
        return totalprice;
    }

    public void finalizeReservedOrder(Integer userId, Order orderId,List<ReservedOrder> listOfAllReservedOrdersOfUser) {
   for (ReservedOrder reservedOrder: listOfAllReservedOrdersOfUser){
       reservedOrder.setOrderId(orderId);
       reservedOrderRepository.UpdateReservedOrder(reservedOrder);
   }

    }

    public List<ReservedOrder> findReservedOrderByUserId(Integer userId) {
        return reservedOrderRepository.findReservedOrderByPerson_IdWhereOrder_IDIsNull(userId);
    }
}
