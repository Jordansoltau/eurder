package com.example.eurder.service;

import com.example.eurder.Repositories.ReservedOrderRepository;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservedOrderService {
    private final ReservedOrderRepository reservedOrderRepository;

    public ReservedOrderService(ReservedOrderRepository reservedOrderRepository) {
        this.reservedOrderRepository = reservedOrderRepository;
    }
    public void finalizeReservedOrder( Order orderId,List<ReservedOrder> listOfAllReservedOrdersOfUser) {
   for (ReservedOrder reservedOrder: listOfAllReservedOrdersOfUser){
       reservedOrder.setOrderId(orderId);
       reservedOrderRepository.UpdateReservedOrder(reservedOrder);
   }
    }

    public List<ReservedOrder> findReservedOrderByUserId(Integer userId) {
        return reservedOrderRepository.findReservedOrderByPerson_IdWhereOrder_IDIsNull(userId);
    }

    public double getTotalPrice(List<ReservedOrder> listOfAllReservedOrdersOfUser) {
        double totalPrice = 0;
        for (ReservedOrder reservedOrder:listOfAllReservedOrdersOfUser){
            totalPrice+= reservedOrder.getItemGroep().getPriceOfOrder();
        }
        return totalPrice;
    }
}
