package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.Repositories.ReservedOrderRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Person;
import com.example.eurder.dto.ItemGroepClientViewDTO;
import com.example.eurder.dto.OrderDTO;
import com.example.eurder.exceptions.NotFoundexception;
import com.example.eurder.exceptions.UnknownPersonException;
import com.example.eurder.mapper.OrderMapper;

import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final ReservedOrderRepository reservedOrderRepository;
    private final ReservedOrderService reservedOrderService;
    private final OrderMapper orderMapper;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , OrderRepository orderRepository, ItemRepository itemRepository, ItemMapper itemMapper, UserRepository userRepository, ReservedOrderRepository reservedOrderRepository, ReservedOrderService reservedOrderService, OrderMapper orderMapper) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
        this.reservedOrderRepository = reservedOrderRepository;
        this.reservedOrderService = reservedOrderService;
        this.orderMapper = orderMapper;
    }

    public void createAnOrder(String authorization, ItemGroepDto itemGroepDto, Integer userId) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        securityService.validateUserAndAuthorization(authorization, userId);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        Person person = userRepository.findById(userId).orElseThrow(() -> new UnknownPersonException());

        ReservedOrder reservedOrder = itemMapper.fromItemGroepDTOToReservedOrder(itemGroepDto, person);
        reservedOrderRepository.saveAnOrder(reservedOrder);

        Item item = itemRepository.findById(itemGroepDto.getItemId()).orElseThrow();
        item.decreaseAmount(itemGroepDto.getAmountToPurchase());
        itemRepository.save(item);
    }


    public List<Order> getAllOrderOfItemsWithoutAuthorization() {
        return orderRepository.findAll();
        //not ok
    }

    public OrderDTO confirmReservedItems(String authorization, Integer userId) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        securityService.validateUserAndAuthorization(authorization, userId);

        if(reservedOrderService.findReservedOrderByUserId(userId).isEmpty()){
            throw new IllegalArgumentException("There are no reserved orders for this member");
        }

        Person person = userRepository.findById(userId).orElseThrow(()->new NotFoundexception());
        double totalPrice = reservedOrderService.getTotalprice(userId);
        Order order = new Order(person,totalPrice);
        orderRepository.save(order);

        List<ReservedOrder> listOfAllReservedOrdersOfUser =  reservedOrderRepository.findReservedOrderByPerson_IdWhereOrder_IDIsNull(userId);
        reservedOrderService.finalizeReservedOrder(userId,order,listOfAllReservedOrdersOfUser);

        List<ItemGroep> orderedItems = listOfAllReservedOrdersOfUser.stream().map(ReservedOrder::getItemGroep).collect(Collectors.toList());
        List<ItemGroepClientViewDTO> itemGroepClientViewDTOList = itemMapper.mapFromItemGroepListToItemGroepClientViewDTOList(orderedItems);
        return orderMapper.mapFromOrderToOrderDto(order,itemGroepClientViewDTOList);
    }


//    public List<Order> getOrderOfItems(String authorization, Integer userId) {
//        securityService.validateAuthorization(authorization, Feature.ADMIN);
//        return orderRepository.findAll();
//    }
}
