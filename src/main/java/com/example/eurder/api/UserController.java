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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Person addANewUser(@RequestBody UserDto userDto) {
        return userService.createANewAccount(userDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Person> getAllUsers() {
        return userService.getAllUsers();
        //Danger!! ask Tim
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Person getUserInformation( @Parameter Integer id) {
        return userService.getUsers( id);
        //Danger!! ask Tim
    }

//    @GetMapping(path = "/{id}/my-orders", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public List<OrderDTO> getUserOrders( @RequestHeader String authorization, @PathVariable String id){
//        return userService.getReportOfOrders(authorization,id);
//        //not green requirement
//    }
}
