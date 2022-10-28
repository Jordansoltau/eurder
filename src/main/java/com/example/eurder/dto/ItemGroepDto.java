package com.example.eurder.dto;

public class ItemGroepDto {
    private final String itemId;
    private final int amountToPurchase;

    public ItemGroepDto(String itemId, int amountToPurchase) {
        this.itemId = itemId;
        this.amountToPurchase = amountToPurchase;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmountToPurchase() {
        return amountToPurchase;
    }
}
