package com.example.eurder.api;

import com.example.eurder.service.dto.orderDto.ItemGroepDto;

import com.example.eurder.service.dto.orderDto.OrderDTO;
import com.example.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping("orders")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void AddItemToReservedOrder(@RequestHeader String authorization, @RequestBody ItemGroepDto itemgroepDto, @PathVariable Integer userId) {
        orderService.createAReservation(authorization, itemgroepDto,userId);
    }

    @PostMapping(path = "/{userId}/confirm",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO confirmOrder(@RequestHeader String authorization, @PathVariable Integer userId){
        return orderService.confirmReservedItems(authorization,userId);
    }


}
