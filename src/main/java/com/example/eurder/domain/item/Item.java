package com.example.eurder.domain.item;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "amountstock")
    private int amount;

    public Item( String name, String description, double price, int amount) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Item(String id, String name, String description, double price, int amount) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Item() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void decreaseAmount(int amountToPurchase) {
        this.amount -= amountToPurchase;
    }
}
