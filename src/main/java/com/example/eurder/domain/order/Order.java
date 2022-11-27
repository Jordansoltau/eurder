package com.example.eurder.domain.order;

import com.example.eurder.domain.user.Person;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq",allocationSize = 1)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Person userid;
    @Column(name = "totalprice")
    private double totalPrice;

    public Order() {
    }

    public Order(Person userid, double totalPrice) {
        this.userid = userid;
        this.totalPrice = totalPrice;
    }
//getters




    public Integer getId() {
        return id;
    }


    public Person getUserid() {
        return userid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }



    //setters
    public void setOrderId(Integer orderId) {
        this.id = orderId;
    }



    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserid(Person person) {
        this.userid = person;
    }


}
