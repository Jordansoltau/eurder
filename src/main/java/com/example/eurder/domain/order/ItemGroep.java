package com.example.eurder.domain.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ItemGroep {
    @Column(name = "item_id")
    private  String itemId;
    @Column(name = "amount_purchase")
    private  int amount;
    @Column(name = "shippingdate")
    private  LocalDate shippingdate;
    @Column(name = "priceoforder")
    private  double priceOfOrder;


    public ItemGroep(String itemId, int amount, LocalDate date, double priceOfOrder) {
        this.itemId = itemId;
        this.amount = amount;
        this.shippingdate = date;
        this.priceOfOrder = priceOfOrder;
    }

    public ItemGroep() {

    }

    public double getPriceOfOrder() {
        return priceOfOrder;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingdate() {
        return shippingdate;
    }


}
