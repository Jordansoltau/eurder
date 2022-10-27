package com.example.eurder.Repositories;

import com.example.eurder.domain.item.Item;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRepository {
    private Map<String, Item> itemsList;
    private final ItemMapper itemMapper;

    public ItemRepository(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
        this.itemsList = hardCodedListOfItems();
    }

    private Map<String, Item> hardCodedListOfItems() {
        Item mouse = new Item("Mouse", "object for pc use only clicking",20,5);
        Item keyboard = new Item("Keyboard", "object for pc use only typing",20,5);
        HashMap<String,Item> hardCodedRepository = new HashMap<>();
        hardCodedRepository.put(mouse.getId(),mouse);
        hardCodedRepository.put(mouse.getId(),mouse);
        return hardCodedRepository;
    }
    public void addNewItem(ItemDto itemDto) {
        Item itemToAdd = itemMapper.fromDtoToItem(itemDto);
        itemsList.put(itemToAdd.getId(),itemToAdd);
    }
}
