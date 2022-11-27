package com.example.eurder.Repositories;

import com.example.eurder.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
