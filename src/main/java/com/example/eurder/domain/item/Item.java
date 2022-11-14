package com.example.eurder.domain.item;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    private String id;
    @Column(name = "item_description")
    private String description;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_price")
    private double price;
    @Column(name = "item_amount_stock")
    private int amount;

    public Item( String name, String description, double price, int amount) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Item() {

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
