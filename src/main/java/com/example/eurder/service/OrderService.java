package com.example.eurder.service;

import com.example.eurder.domain.item.Item;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , OrderRepository orderRepository, ItemRepository itemRepository, ItemMapper itemMapper) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;

    }

    public void createAnOrder(String authorization, ItemGroepDto itemGroepDto, String userId) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        securityService.validateUserAndAuthorization(authorization, userId);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());

        Order order = itemMapper.fromItemGroepDTOToOrder(itemGroepDto);
        orderRepository.save(order);

        Item item = itemRepository.findById(itemGroepDto.getItemId()).orElseThrow();
        item.decreaseAmount(itemGroepDto.getAmountToPurchase());
        itemRepository.save(item);
    }


    public List<Order> getOrderOfItems(String authorization) {
        securityService.validateAuthorization(authorization, Feature.ADMIN);
        return orderRepository.findAll();
    }

    public List<Order> getAllOrderOfItemsWithoutAuthorization(String userId) {
        return  orderRepository.findAllById(Collections.singleton(userId));
        //not ok
    }
}
