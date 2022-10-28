package com.example.eurder.mapper;

import com.example.eurder.domain.item.Item;
import com.example.eurder.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item fromDtoToItem(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }
}
