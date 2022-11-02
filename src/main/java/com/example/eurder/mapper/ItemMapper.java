package com.example.eurder.mapper;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemMapper {

    public Item fromDtoToItem(ItemDto itemDto) {
        return new Item("1",itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }

    public ItemGroep fromItemGroepDtoToItemGroep(ItemGroepDto itemGroepDto, LocalDate date, double price) {
        return new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase(), date, price);
    }


}
