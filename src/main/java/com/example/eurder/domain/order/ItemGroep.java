package com.example.eurder.domain.order;

import com.example.eurder.Repositories.ItemRepository;

import java.time.LocalDate;

public class ItemGroep {
    private final String itemId;
    private final int amount;
    private final LocalDate shipingDate;


    public ItemGroep(String itemId, int amount, LocalDate date) {
        this.itemId = itemId;
        this.amount = amount;
        this.shipingDate = date;
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
