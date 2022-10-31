package com.example.eurder.Repositories;

import com.example.eurder.domain.item.Item;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRepository {

    public static final int MINIMUM_AMOUNT_IN_STOCK = 0;
    private Map<String, Item> itemsList;

    public ItemRepository() {
        this.itemsList = hardCodedListOfItems();
    }



    private Map<String, Item> hardCodedListOfItems() {
        Item mouse = new Item("Mouse", "object for pc use only clicking", 20, 5);
        Item keyboard = new Item("Keyboard", "object for pc use only typing", 20, 5);
        HashMap<String, Item> hardCodedRepository = new HashMap<>();
        hardCodedRepository.put(mouse.getId(), mouse);
        hardCodedRepository.put(keyboard.getId(), mouse);
        System.out.println(mouse.getId());
        return hardCodedRepository;
    }

    public void addNewItem(Item item) {
        itemsList.put(item.getId(), item);
    }

    public boolean itemHasStock(String itemId) {
        Item itemInStock = itemsList.get(itemId);
        return itemInStock.getAmount() > MINIMUM_AMOUNT_IN_STOCK;
    }

    public boolean doesItemExist(String itemid) {
        return itemsList.get(itemid) != null;
    }


}
