package com.example.eurder.domain.order;

import com.example.eurder.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDate;

@Embeddable
public class ItemGroep {
    @ManyToOne
    @JoinColumn(name = "itemid")
    private Item itemId;
    @Column(name = "amountpurchase")
    private  int amount;
    @Column(name = "shippingdate")
    private  LocalDate shippingdate;
    @Column(name = "priceoforder")
    private  double priceOfOrder;


    public ItemGroep(Item itemId, int amount, LocalDate shippingdate, double priceOfOrder) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingdate = shippingdate;
        this.priceOfOrder = priceOfOrder;
    }

    public ItemGroep() {

    }

    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public Item getItem() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingdate() {
        return shippingdate;
    }


}
