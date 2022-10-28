package com.example.eurder.domain.order;

import com.example.eurder.Repositories.ItemRepository;

import java.time.LocalDate;

public class ItemGroep {

    private String itemId;
    private int amount;
    private LocalDate shipingDate;


    public ItemGroep(String itemId, int amount , LocalDate shipingDate) {
        this.itemId = itemId;
        this.amount = amount;
        this.shipingDate = shipingDate;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setShipingDate(LocalDate shipingDate) {
        this.shipingDate = shipingDate;
    }

}
