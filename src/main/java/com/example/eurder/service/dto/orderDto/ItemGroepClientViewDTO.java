package com.example.eurder.service.dto.orderDto;

import java.time.LocalDate;

public record ItemGroepClientViewDTO (
        int amount,
        LocalDate shippingDate,
        double priceOfItemGroep,
        OrderedItemsDto item
){
}
