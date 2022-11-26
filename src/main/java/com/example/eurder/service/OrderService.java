package com.example.eurder.service;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Person;
import com.example.eurder.exceptions.NotFoundexception;
import com.example.eurder.exceptions.UnknownPersonException;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.ReservedOrderRepository;
import com.example.eurder.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final ReservedOrderRepository reservedOrderRepository;
    private final ReservedOrderService reservedOrderService;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , OrderRepository orderRepository, ItemRepository itemRepository, ItemMapper itemMapper, UserRepository userRepository, ReservedOrderRepository reservedOrderRepository, ReservedOrderService reservedOrderService) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
        this.reservedOrderRepository = reservedOrderRepository;
        this.reservedOrderService = reservedOrderService;
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

    public Order confirmReservedItems(String authorization, Integer userId) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        securityService.validateUserAndAuthorization(authorization, userId);
        Person person = userRepository.findById(userId).orElseThrow(()->new NotFoundexception());

        if(reservedOrderService.findReservedOrderByUserId(userId).isEmpty()){
            throw new IllegalArgumentException("There are no reserved orders for this member");
        }

        double totalPrice = reservedOrderService.getTotalprice(userId);

        Order order = new Order(person,totalPrice);
        orderRepository.save(order);
        //set orderIdInReservedOrder
        reservedOrderService.finalizeReservedOrder(userId,order);
        //retunr order
        return order;
    }


//    public List<Order> getOrderOfItems(String authorization, Integer userId) {
//        securityService.validateAuthorization(authorization, Feature.ADMIN);
//        return orderRepository.findAll();
//    }
}
