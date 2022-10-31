package com.example.eurder.mapper;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item fromDtoToItem(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }

    public ItemGroep fromItemGroepDtoToItemGroep(ItemGroepDto itemGroepDto) {
        return new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase());
    }
}
