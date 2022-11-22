package com.example.eurder.mapper;

import com.example.eurder.exceptions.NotFoundexception;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemMapper {
    private final ItemRepository itemRepository;
    public static final int DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK = 7;

    public ItemMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemGroep fromItemGroepDtoToItemGroep(ItemGroepDto itemGroepDto) {

        return new ItemGroep(itemRepository.findById(itemGroepDto.getItemId()).orElseThrow(()-> new NotFoundexception()), itemGroepDto.getAmountToPurchase(), setShippingDate(itemGroepDto), calculatePriceOfOrder(itemGroepDto));
    }


    private double calculatePriceOfOrder(ItemGroepDto itemGroepDto) {
        Item item = itemRepository.findById(itemGroepDto.getItemId()).orElseThrow();
        return itemGroepDto.getAmountToPurchase() * item.getPrice();
    }

    private LocalDate setShippingDate(ItemGroepDto itemGroepDto) {
        Item item = itemRepository.findById(itemGroepDto.getItemId()).orElseThrow();
        if (itemGroepDto.getAmountToPurchase() <= item.getAmount()) {
            return LocalDate.now();
        } else {
            return LocalDate.now().plusDays(DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK);
        }
    }

    //Order should not lose information
    public Order fromItemGroepDTOToOrder(ItemGroepDto itemGroepDto) {
        ItemGroep itemGroep = fromItemGroepDtoToItemGroep(itemGroepDto);
        return new Order(itemGroep);
    }


    public Item fromItemDtoToItem(ItemDto itemDto, String itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        item.setName(itemDto.getName());
        item.setAmount(itemDto.getAmount());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        return item;
    }

    public Item fromItemDtoToItemWhenCreatingItem(ItemDto itemDto, String itemId) {
        return new Item(itemId,itemDto.getName(),itemDto.getDescription(),itemDto.getPrice(),itemDto.getAmount());
    }
}
