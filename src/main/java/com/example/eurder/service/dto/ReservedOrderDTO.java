package com.example.eurder.service.dto;

import java.time.LocalDate;

public record ReservedOrderDTO(
       String itemId,
        String name,
        int amount,
        LocalDate shippingDate,
       double priceOfItems
) {
}
