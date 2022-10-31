package com.example.eurder.domain.order;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Order {

    private final Map<String, ArrayList<ItemGroep>> copyOfOrderRepository;
    private final OrderRepository orderRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final ValidationItemService validationItemService;

    public Order(OrderRepository orderRepository, ItemMapper itemMapper, ItemRepository itemRepository, ValidationItemService validationItemService) {
        this.orderRepository = orderRepository;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.validationItemService = validationItemService;
        this.copyOfOrderRepository = new HashMap<>();

    }

    public void orderNewItem(String userId, ItemGroepDto itemGroepDto) {

        LocalDate shippingDate = setShippingDate(itemGroepDto);

        if (copyOfOrderRepository.get(userId) == null) {
            ArrayList<ItemGroep> newList = new ArrayList<>();
            newList.add(itemMapper.fromItemGroepDtoToItemGroep(itemGroepDto, shippingDate));
            copyOfOrderRepository.put(userId, newList);
            orderRepository.saveCurrentOrder(userId, newList);
            itemRepository.updateStock(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase());
            return;
        }

        ArrayList<ItemGroep> updateList = copyOfOrderRepository.get(userId);
        updateList.add(itemMapper.fromItemGroepDtoToItemGroep(itemGroepDto, shippingDate));
        copyOfOrderRepository.put(userId, updateList);
        orderRepository.saveCurrentOrder(userId, updateList);
        itemRepository.updateStock(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase());


    }

    private LocalDate setShippingDate(ItemGroepDto itemGroepDto) {
                if (itemGroepDto.getAmountToPurchase()<itemRepository.getItemOnId(itemGroepDto.getItemId()).getAmount()){
                    return LocalDate.now();
                }else {
                    return LocalDate.now().plusDays(7);
                }
    }


    public List<ItemGroep> getOrder(String id) {
        return copyOfOrderRepository.get(id);

    }
}
