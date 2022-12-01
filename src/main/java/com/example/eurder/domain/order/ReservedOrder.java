package com.example.eurder.domain.order;

import com.example.eurder.domain.user.Person;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reserved_order")
public class ReservedOrder {

    @Id
    @Column(name = "reserved_order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserved_order_seq")
    @SequenceGenerator(name = "reserved_order_seq", sequenceName = "reserved_order_seq", allocationSize = 1)
    private Integer id;

    @Embedded
    private ItemGroep itemGroep;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Person person;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Order order;

    public ReservedOrder() {
    }

    public ReservedOrder(ItemGroep itemGroep, Person person) {
        this.itemGroep = itemGroep;
        this.person = person;
        order = null;
    }

    public Integer getId() {
        return id;
    }

    public ItemGroep getItemGroep() {
        return itemGroep;
    }

    public Person getPerson() {
        return person;
    }

    public void setOrderId(Order orderId) {
        this.order = orderId;
    }
}
