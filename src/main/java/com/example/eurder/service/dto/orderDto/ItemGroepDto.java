package com.example.eurder.service.dto.orderDto;

import java.time.LocalDate;

public class ItemGroepDto {
    private final String itemId;
    private final int amountToPurchase;
    private final LocalDate shippingDate;

    public ItemGroepDto(String itemId, int amountToPurchase, LocalDate date) {
        this.itemId = itemId;
        this.amountToPurchase = amountToPurchase;
        this.shippingDate = date;
    }



    public String getItemId() {
        return itemId;
    }

    public int getAmountToPurchase() {
        return amountToPurchase;
    }
}
