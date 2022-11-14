package com.example.eurder.api;

import com.example.eurder.domain.order.Order;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.dto.OrderDTO;
import com.example.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("orders")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void AddItemToReservedOrder(@RequestHeader String authorization, @RequestBody ItemGroepDto itemgroepDto, @PathVariable String userId) {
        orderService.createAnOrder(authorization, itemgroepDto,userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> confirmOrder(@RequestHeader String authorization){
        return orderService.getOrderOfItems(authorization);
    }
}
