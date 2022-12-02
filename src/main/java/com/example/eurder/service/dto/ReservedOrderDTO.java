package com.example.eurder.service.dto;

import java.time.LocalDate;

public record ReservedOrderDTO(
        int id,
       String itemId,
        String name,
        int amount,
        LocalDate shippingDate,
       double priceOfItems
) {
}
