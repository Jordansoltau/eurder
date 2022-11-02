package com.example.eurder.api;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.dto.ItemGroepDto;
import com.example.eurder.dto.UserDto;
import com.example.eurder.service.OrderService;
import com.example.eurder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addANewItem(@RequestBody UserDto userDto) {
        userService.createANewAccount(userDto);
    }

}
