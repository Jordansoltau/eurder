package com.example.eurder.domain.order;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Order {

    public static final int DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK = 7;
    private final Map<String, ArrayList<ItemGroep>> currentOrder;
    private final OrderRepository orderRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;


    public Order(OrderRepository orderRepository, ItemMapper itemMapper, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.currentOrder = new HashMap<>();

    }

    public void orderNewItem(String userId, ItemGroepDto itemGroepDto) {

        LocalDate shippingDate = setShippingDate(itemGroepDto);
        double priceOfOrder = calculatePriceOfOrder(itemGroepDto);

        if (currentOrder.get(userId) == null) {
            ArrayList<ItemGroep> newList = new ArrayList<>();
            newList.add(itemMapper.fromItemGroepDtoToItemGroep(itemGroepDto, shippingDate,priceOfOrder));
            currentOrder.put(userId, newList);
            orderRepository.saveCurrentOrder(userId, newList);
            itemRepository.updateStock(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase());
            return;
        }

        ArrayList<ItemGroep> updateList = currentOrder.get(userId);
        updateList.add(itemMapper.fromItemGroepDtoToItemGroep(itemGroepDto, shippingDate,priceOfOrder));
        currentOrder.put(userId, updateList);
        orderRepository.saveCurrentOrder(userId, updateList);
        itemRepository.updateStock(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase());


    }

    private double calculatePriceOfOrder(ItemGroepDto itemGroepDto) {
        return itemGroepDto.getAmountToPurchase()*itemRepository.getItemOnId(itemGroepDto.getItemId()).getPrice();
    }

    private LocalDate setShippingDate(ItemGroepDto itemGroepDto) {
                if (itemGroepDto.getAmountToPurchase()<itemRepository.getItemOnId(itemGroepDto.getItemId()).getAmount()){
                    return LocalDate.now();
                }else {
                    return LocalDate.now().plusDays(DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK);
                }
    }


    public List<Receipt> getOrder(String id) {
        List<ItemGroep> orderedItems = currentOrder.get(id);
        if (orderedItems==null){
            throw new IllegalArgumentException("There is nothing ordered yet");
        }
        Receipt receiptOfUser = new Receipt(orderedItems,caclculateTotalPriceOfOrder(orderedItems), id);
        List<Receipt> receipt =new ArrayList<>();
        receipt.add(receiptOfUser);
        return receipt;

    }

    private static double caclculateTotalPriceOfOrder(List<ItemGroep> orderedItems) {
        double totalOrderPrice = 0;
        if (orderedItems==null){
            return totalOrderPrice;
        }
        for (ItemGroep items: orderedItems){
            totalOrderPrice+=items.getPriceOfOrder();
        }
        return totalOrderPrice;
    }
}
