package com.example.eurder.api;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.Receipt;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.service.OrderService;
import com.example.eurder.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("orders")
@RestController
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public OrderController( OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void orderOneItem(@RequestHeader String authorization, @RequestBody ItemGroepDto itemgroepDto) {
        orderService.createANewOrder(authorization, itemgroepDto);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Receipt> getOrderOfUser(@RequestHeader String authorization, @PathVariable String id){
        return orderService.getOrderOfItems(authorization,id);
    }


}
