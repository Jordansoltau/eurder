package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import com.example.eurder.service.validation.ValidationUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final ValidationUserService validationUserService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , ValidationUserService validationUserService
            , OrderRepository orderRepository
            , ItemRepository itemRepository
            , ItemMapper itemMapper)
    {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.validationUserService = validationUserService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public void createANewOrder(String authorization, ItemGroepDto itemGroepDto) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        LocalDate dayOfOrder = itemRepository.dateDependingOnStock(itemGroepDto.getItemId());
        ItemGroep itemGroep = new ItemGroep(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase(),dayOfOrder);
        orderRepository.addNewOrder(itemGroep);
    }
}
