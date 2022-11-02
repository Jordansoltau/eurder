package com.example.eurder.service;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.Receipt;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import com.example.eurder.service.validation.ValidationUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class OrderService {
    private final SecurityService securityService;
    private final ValidationItemService validationItemService;
    private final Order order;


    public OrderService(SecurityService securityService
            , ValidationItemService validationItemService
            , Order order) {
        this.securityService = securityService;
        this.validationItemService = validationItemService;
        this.order = order;
    }

    public void createANewOrder(String authorization, ItemGroepDto itemGroepDto) {
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEM);
        String userId = securityService.getUserId(authorization);
        validationItemService.validateIfItemExist(itemGroepDto.getItemId());
        order.orderNewItem(userId,itemGroepDto);
    }

    public List<Receipt> getOrderOfItems(String authorization, String id) {
        securityService.validateAuthorization(authorization,Feature.ORDER_ITEM);
        return order.getOrder(id);
    }
}
