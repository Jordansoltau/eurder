package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , OrderRepository orderRepository, UserRepository userRepository, ItemMapper itemMapper) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;

    }

    public void addItemToUser(String authorization, ItemGroepDto itemGroepDto) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        String userId = securityService.getUserId(authorization);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        ItemGroep itemGroep = itemMapper.fromItemGroepDtoToItemGroep(itemGroepDto);
        userRepository.createOrderInUserCart(userId, itemGroep);
    }


    public Order getOrderOfItems(String authorization) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        String userId = securityService.getUserId(authorization);
        ArrayList<ItemGroep> currentOrder = userRepository.confirmOrderOfUser(userId);
        String orderId = orderRepository.saveOrder(currentOrder);
        double totalPrice = calculateTotalOfOrder(orderId);
        return itemMapper.mapFromItemGroepToOrder(orderId,currentOrder, totalPrice, userId);
    }

    private double calculateTotalOfOrder(String orderId) {
        ArrayList<ItemGroep> orderOfCustomer = orderRepository.getByOrderId(orderId);
        return orderOfCustomer.stream().mapToDouble(ItemGroep::getPriceOfOrder).sum();
    }


}
