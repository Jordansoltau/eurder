package com.example.eurder.service;

import com.example.eurder.dto.OrderDTO;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.List;


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
        securityService.validateUserAndAuthorization(authorization,userId);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        Order order = itemMapper.fromItemGroepDTOToOrder(itemGroepDto);
        orderRepository.addOrder(userId, order);
        itemRepository.updateStock(itemGroepDto.getItemId(),itemGroepDto.getAmountToPurchase());
    }


    public List<OrderDTO> getOrderOfItems(String authorization) {
        securityService.validateAuthorization(authorization, Feature.ADMIN);
        return itemMapper.fromOrderRepositoryToListOrderDTO(orderRepository.getOrderRepository());

    }


}
