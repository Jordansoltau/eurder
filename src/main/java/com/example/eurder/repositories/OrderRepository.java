package com.example.eurder.repositories;

import com.example.eurder.domain.order.ItemGroep;

import com.example.eurder.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<ArrayList<Order>,String> {


    private Map<String, ArrayList<Order>> hardCodedRepository() {
        Map<String, ArrayList<Order>> hardCodedRepository = new HashMap<>();
        Order orderHardCoded = new Order(new ItemGroep("10", 1, LocalDate.now(), 20));
        ArrayList<Order> listOfOrders = new ArrayList<>();
        listOfOrders.add(orderHardCoded);
        hardCodedRepository.put("1", listOfOrders);
        return hardCodedRepository;
    }


    public void addOrder(String userId, Order order) {
        ArrayList<Order> ListOfOrderOfUser = updateOrdersForUser(userId, order);
        orderRepository.put(userId,ListOfOrderOfUser);
    }

    private ArrayList<Order> updateOrdersForUser(String userId, Order order) {
        if (orderRepository.get(userId) == null){
            ArrayList<Order> ListOfOrderOfUser = new ArrayList<>();
            ListOfOrderOfUser.add(order);
            return ListOfOrderOfUser;

        }
        ArrayList<Order> ListOfOrderOfUser = orderRepository.get(userId);
        ListOfOrderOfUser.add(order);
        return ListOfOrderOfUser;
    }

    public Map<String, ArrayList<Order>> getOrderRepository() {
        return orderRepository;
    }
}
