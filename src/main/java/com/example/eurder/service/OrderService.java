package com.example.eurder.service;

import com.example.eurder.dto.OrderDTO;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
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
import java.util.Map;


@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final OrderRepository orderRepository;
    private final ItemMapper itemMapper;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , OrderRepository orderRepository, ItemMapper itemMapper) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.itemMapper = itemMapper;

    }

    public void createAnOrder(String authorization, ItemGroepDto itemGroepDto, String userId) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        securityService.validateUserAndAuthorization(authorization,userId);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        Order order = itemMapper.fromItemGroepDTOToOrder(itemGroepDto);
        orderRepository.addOrder(userId, order);
    }


    public List<OrderDTO> getOrderOfItems(String authorization) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        return itemMapper.fromOrderRepositoryToListOrderDTO(orderRepository.getOrderRepository());

    }


}
