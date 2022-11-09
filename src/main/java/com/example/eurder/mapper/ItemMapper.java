package com.example.eurder.mapper;

import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class ItemMapper {
    private final ItemRepository itemRepository;
    public static final int DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK = 7;

    public ItemMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

    }

    public Item fromDtoToItem(ItemDto itemDto) {
        return new Item("1",itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }

    public ItemGroep fromItemGroepDtoToItemGroep(ItemGroepDto itemGroepDto) {
        return new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase(), setShippingDate(itemGroepDto), calculatePriceOfOrder(itemGroepDto));
    }

    private double calculatePriceOfOrder(ItemGroepDto itemGroepDto) {
        return itemGroepDto.getAmountToPurchase() * itemRepository.getItemOnId(itemGroepDto.getItemId()).getPrice();
    }

    private LocalDate setShippingDate(ItemGroepDto itemGroepDto) {
        if (itemGroepDto.getAmountToPurchase() < itemRepository.getItemOnId(itemGroepDto.getItemId()).getAmount()) {
            return LocalDate.now();
        } else {
            return LocalDate.now().plusDays(DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK);
        }
    }

    //Order should not lose information
    public Order fromItemGroepDTOToOrder(String userId, ItemGroepDto itemGroepDto) {
        ItemGroep itemGroep=fromItemGroepDtoToItemGroep(itemGroepDto);
        Order order = new Order();
        order.addOrder(userId,itemGroep);
        return order;
    }
}
