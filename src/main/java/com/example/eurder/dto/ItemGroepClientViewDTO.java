package com.example.eurder.dto;

import java.time.LocalDate;
import java.util.Date;

public record ItemGroepClientViewDTO (
        int amount,
        LocalDate shippingDate,
        double priceOfItemGroep,
        OrderedItemsDto item
){
}
