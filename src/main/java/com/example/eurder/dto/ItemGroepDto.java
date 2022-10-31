package com.example.eurder.dto;

import java.time.LocalDate;

public class ItemGroepDto {
    private final String itemId;
    private final int amountToPurchase;
    private final LocalDate shippingDate;

    public ItemGroepDto(String itemId, int amountToPurchase) {
        this.itemId = itemId;
        this.amountToPurchase = amountToPurchase;
        this.shippingDate = getShippingDate();
    }

    private static LocalDate getShippingDate() {
        return LocalDate.now();
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmountToPurchase() {
        return amountToPurchase;
    }
}
