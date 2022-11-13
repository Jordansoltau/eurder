package com.example.eurder.mapper;

import com.example.eurder.dto.OrderDTO;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItemMapper {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    public static final int DAYS_TO_ADD_IF_NOT_ENOUGH_STOCK = 7;

    public ItemMapper(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public Item fromDtoToItem(ItemDto itemDto, String itemId) {
        return new Item(itemId, itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }

    public ItemGroep fromItemGroepDtoToItemGroep(ItemGroepDto itemGroepDto) {
        return new ItemGroep(itemGroepDto.getItemId(), itemGroepDto.getAmountToPurchase(), setShippingDate(itemGroepDto), calculatePriceOfOrder(itemGroepDto));
    }

    private double calculatePriceOfOrder(ItemGroepDto itemGroepDto) {
        return itemGroepDto.getAmountToPurchase() * itemRepository.getItemOnId(itemGroepDto.getItemId()).getPrice();
    }

    private LocalDate setShippingDate(ItemGroepDto itemGroepDto) {
        if (itemGroepDto.getAmountToPurchase() <= itemRepository.getItemOnId(itemGroepDto.getItemId()).getAmount()) {
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

    public List<OrderDTO> fromOrderRepositoryToListOrderDTO(Map<String, ArrayList<Order>> orderRepo) {
        List<OrderDTO> orders = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Order>> entry : orderRepo.entrySet()) {
            String userId = entry.getKey();
            orders.add(new OrderDTO(orderRepo.get(userId),userId));
        }
        return orders;
    }



}
