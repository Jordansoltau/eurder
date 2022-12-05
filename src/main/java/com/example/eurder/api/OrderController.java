package com.example.eurder.api;

import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.service.dto.ReservedOrderDTO;
import com.example.eurder.service.dto.orderDto.ItemGroepDto;

import com.example.eurder.service.dto.orderDto.OrderDTO;
import com.example.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("orders")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ReservedOrder AddItemToReservedOrder( @RequestBody ItemGroepDto itemgroepDto, @PathVariable Integer userId) {
       return orderService.createAReservation(itemgroepDto,userId);
    }

    @PostMapping(path = "/{userId}/confirm",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderDTO confirmOrder( @PathVariable Integer userId){
        return orderService.confirmReservedItems(userId);
    }

@GetMapping(path = "/reservedorders",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//@PreAuthorize("hasAuthority('ADMIN')")
    public List<ReservedOrderDTO> getallreservedOrders(@RequestParam String userName){
        return orderService.getAllReservedOrders(userName);
}
}
