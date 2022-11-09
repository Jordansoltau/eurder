package com.example.eurder.repositories;

import com.example.eurder.domain.item.Item;
import org.springframework.stereotype.Component;

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
        Item mouse = new Item("10","Mouse", "object for pc use only clicking", 20, 5);
        Item keyboard = new Item("11","Keyboard", "object for pc use only typing", 20, 5);
        HashMap<String, Item> hardCodedRepository = new HashMap<>();
        hardCodedRepository.put(mouse.getId(), mouse);
        hardCodedRepository.put(keyboard.getId(), keyboard);
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

    public Item getItemOnId(String itemId){
        return itemsList.get(itemId);
    }

    public void updateStock(String itemId,int amountToPurchase) {
        Item itemToUpdate = itemsList.get(itemId);
        itemToUpdate.decreaseAmount(amountToPurchase);
    }
}
