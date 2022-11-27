package com.example.eurder.Repositories;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.ReservedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;



@Repository
@Transactional
public class ReservedOrderRepository{
    //    ReservedOrder findReservedOrderByUserId(Integer userId);

    @PersistenceContext
    private final EntityManager entityManager;

    public ReservedOrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<ReservedOrder> findReservedOrderByPerson_IdWhereOrder_IDIsNull(Integer userId){
        return entityManager.createQuery("select a from ReservedOrder a where a.person.id ='"+ userId +"' AND a.order IS NULL", ReservedOrder.class)
                .getResultList();
    }

    public List<ReservedOrder> findReservedOrderByUserId(Integer userId) {
        return entityManager.createQuery("select a from ReservedOrder a where a.person.id ='"+ userId +"'", ReservedOrder.class)
                .getResultList();
    }

    public void saveAnOrder(ReservedOrder reservedOrder) {
        entityManager.persist(reservedOrder);
    }

    public void UpdateReservedOrder(ReservedOrder reservedOrder) {
        entityManager.merge(reservedOrder);
    }


}
