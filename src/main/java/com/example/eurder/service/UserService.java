package com.example.eurder.service;

import com.example.eurder.domain.user.Feature;
import com.example.eurder.domain.user.Person;
import com.example.eurder.mapper.OrderMapper;
import com.example.eurder.service.dto.personDto.UserDto;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import com.example.eurder.service.validation.ValidationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);
    private final ValidationUserService validationUserService;
    private final SecurityService securityService;
    private final OrderService orderService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    public UserService(UserRepository userRepository, ValidationUserService validationUserService, SecurityService securityService, OrderService orderService, UserMapper userMapper, OrderMapper orderMapper) {
        this.userRepository = userRepository;
        this.validationUserService = validationUserService;
        this.securityService = securityService;
        this.orderService = orderService;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    public Person createANewAccount(UserDto userDto) {
        validationUserService.validateNewUser(userDto);
        validationUserService.validateFirstName(userDto, "FirstName");
        validationUserService.validateLastName(userDto, "LastName");
        validationUserService.validateEmail(userDto, "Email");
        validationUserService.validateAddressName(userDto, "Adress");
        validationUserService.validatePhoneNumber(userDto, "phoneNumber");
        Person person = userMapper.fromDtoToUser(userDto);
        userRepository.save(person);

        return person;
    }


    public List<Person> getAllUsers(String authorization) {
        securityService.validateAuthorization(authorization,Feature.ADMIN);
        return userRepository.findAll();
    }

    public Person getUsers(String authorization, Integer id) {
        securityService.validateAuthorization(authorization,Feature.ADMIN);
        return userRepository.findById(id).orElseThrow();
    }
}
