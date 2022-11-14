package com.example.eurder.domain.order;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ItemGroep {
    private final String itemId;
    private final int amount;
    private final LocalDate shipingDate;
    private final double priceOfOrder;


    public ItemGroep(String itemId, int amount, LocalDate date, double priceOfOrder) {
        this.itemId = itemId;
        this.amount = amount;
        this.shipingDate = date;
        this.priceOfOrder = priceOfOrder;
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

    public LocalDate getShipingDate() {
        return shipingDate;
    }


}
