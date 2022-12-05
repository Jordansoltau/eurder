package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Person;
import com.example.eurder.repositories.ReservedOrderRepository;
import com.example.eurder.service.dto.ReservedOrderDTO;
import com.example.eurder.service.dto.orderDto.ItemGroepClientViewDTO;
import com.example.eurder.service.dto.orderDto.OrderDTO;
import com.example.eurder.exceptions.NotFoundexception;
import com.example.eurder.exceptions.UnknownPersonException;
import com.example.eurder.mapper.OrderMapper;

import com.example.eurder.domain.order.Order;
import com.example.eurder.service.dto.orderDto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.service.validation.ValidationItemService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final ValidationItemService validationItemService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final ReservedOrderRepository reservedOrderRepository;
    private final ReservedOrderService reservedOrderService;
    private final OrderMapper orderMapper;


    public OrderService(
             ValidationItemService validationItemService
            , OrderRepository orderRepository, ItemRepository itemRepository, ItemMapper itemMapper, UserRepository userRepository, ReservedOrderRepository reservedOrderRepository, ReservedOrderService reservedOrderService, OrderMapper orderMapper) {

        this.validationItemService = validationItemService;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
        this.reservedOrderRepository = reservedOrderRepository;
        this.reservedOrderService = reservedOrderService;
        this.orderMapper = orderMapper;
    }

    public ReservedOrder createAReservation(ItemGroepDto itemGroepDto, Integer userId) {

        validationItemService.validateIfItemExist(itemGroepDto.getItemId());

        Person person = userRepository.findById(userId).orElseThrow(UnknownPersonException::new);
        ReservedOrder reservedOrder = itemMapper.fromItemGroepDTOToReservedOrder(itemGroepDto, person);
        reservedOrderRepository.saveAnOrder(reservedOrder);

        Item item = itemRepository.findById(itemGroepDto.getItemId()).orElseThrow();
        item.decreaseAmount(itemGroepDto.getAmountToPurchase());
        itemRepository.save(item);

        //return after post
        return reservedOrder;
    }


    public OrderDTO confirmReservedItems(Integer userId) {


        if(reservedOrderService.findReservedOrderByUserId(userId).isEmpty()){
            throw new IllegalArgumentException("There are no reserved orders for this member");
        }

        Person person = userRepository.findById(userId).orElseThrow(NotFoundexception::new);
        List<ReservedOrder> listOfAllReservedOrdersOfUser =  reservedOrderRepository.findReservedOrderByPerson_IdWhereOrder_IDIsNull(userId);
        double totalPriceOfReservedOrder = reservedOrderService.getTotalPrice(listOfAllReservedOrdersOfUser);

        Order order = new Order(person,totalPriceOfReservedOrder);
        orderRepository.save(order);

        reservedOrderService.finalizeReservedOrder(order,listOfAllReservedOrdersOfUser);

        //return after post
        List<ItemGroep> orderedItems = listOfAllReservedOrdersOfUser.stream().map(ReservedOrder::getItemGroep).collect(Collectors.toList());
        List<ItemGroepClientViewDTO> itemGroepClientViewDTOList = itemMapper.mapFromItemGroepListToItemGroepClientViewDTOList(orderedItems);
        return orderMapper.mapFromOrderToOrderDto(order,itemGroepClientViewDTOList);
    }


    public List<ReservedOrderDTO> getAllReservedOrders() {
        List<ReservedOrder> listToMap = reservedOrderRepository.getAllReservedOrders();
        return orderMapper.mapFromReservedOrderToReservedOrderDtoList(listToMap);
    }
}
