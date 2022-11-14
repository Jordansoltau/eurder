package com.example.eurder.repositories;

import com.example.eurder.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {



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

    public void updateSpecificItem(Item item) {
        itemsList.put(item.getId(),item);
    }

    public Collection<Item> getAllItems() {
        return itemsList.values();
    }
}
