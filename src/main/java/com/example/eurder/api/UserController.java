package com.example.eurder.api;

import com.example.eurder.domain.user.Person;

import com.example.eurder.service.dto.personDto.UserDto;
import com.example.eurder.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
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
    public void addANewUser(@RequestBody UserDto userDto) {
        userService.createANewAccount(userDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllUsers(@RequestHeader String authorization){
        return userService.getAllUsers(authorization);
        //Danger!! ask Tim
    }
    @GetMapping(params = "id",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person getUserInformation(@RequestHeader String authorization, @Parameter Integer id){
        return userService.getUsers(authorization,id);
        //Danger!! ask Tim
    }

//    @GetMapping(path = "/{id}/my-orders", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public List<OrderDTO> getUserOrders( @RequestHeader String authorization, @PathVariable String id){
//        return userService.getReportOfOrders(authorization,id);
//        //not green requirement
//    }
}
