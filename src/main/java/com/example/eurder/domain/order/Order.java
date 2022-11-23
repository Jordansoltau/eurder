package com.example.eurder.domain.order;

import com.example.eurder.domain.user.Person;

import javax.persistence.*;

@Entity
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq",allocationSize = 1)
    private Integer id;
    @Embedded
    private ItemGroep orderedItems;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid")
    private Person userid;
    @Column(name = "totalprice")
    private double totalPrice;

    public Order() {

    }

    public Order(ItemGroep orderedItems) {
        this.orderedItems = orderedItems;
        this.totalPrice = calculatePriceOfOrder(orderedItems);
    }

    public Order(Integer orderId, ItemGroep orderedItems, Person userid, double totalPrice) {
        this.id = orderId;
        this.orderedItems = orderedItems;
        this.userid = userid;
        this.totalPrice = totalPrice;
    }
    //getters


    public Integer getOrderId() {
        return id;
    }

    public ItemGroep getOrderedItems() {
        return orderedItems;
    }

    public Person getUserid() {
        return userid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculatePriceOfOrder(ItemGroep itemGroep) {
        return itemGroep.getPriceOfOrder();
    }


    //setters
    public void setOrderId(Integer orderId) {
        this.id = orderId;
    }

    public void setOrderedItems(ItemGroep orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserid(Person person) {
        this.userid = person;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
