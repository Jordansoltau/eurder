package com.example.eurder.repositories;

import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedOrderRepository extends JpaRepository<ReservedOrder,Integer> {
}
