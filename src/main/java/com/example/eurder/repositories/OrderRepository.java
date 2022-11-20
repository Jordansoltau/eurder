package com.example.eurder.repositories;

import com.example.eurder.domain.order.ItemGroep;

import com.example.eurder.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

}
