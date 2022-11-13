package com.example.eurder.service;

import com.example.eurder.domain.user.Feature;
import com.example.eurder.dto.OrderDTO;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.dto.UserDto;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import com.example.eurder.service.validation.ValidationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);
    private final ValidationUserService validationUserService;
    private final SecurityService securityService;
    private final OrderService orderService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ValidationUserService validationUserService, SecurityService securityService, OrderService orderService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.validationUserService = validationUserService;
        this.securityService = securityService;
        this.orderService = orderService;
        this.userMapper = userMapper;
    }

    public void createANewAccount(UserDto userDto) {
        validationUserService.validateNewUser(userDto);
        validationUserService.validateFirstName(userDto, "FirstName");
        validationUserService.validateLastName(userDto, "LastName");
        validationUserService.validateEmail(userDto, "Email");
        validationUserService.validateAdressName(userDto, "Adress");
        validationUserService.validatePhoneNumber(userDto, "phoneNumber");
        userRepository.addNewUser(userMapper.fromDtoToUser(userDto));
        logger.info("new user is created");
    }

    public List<OrderDTO> getReportOfOrders(String authorization, String id) {
        securityService.validateAuthorization(authorization, Feature.VIEW_OWN_REPORTS);
        securityService.validateUserAndAuthorization(authorization,id);
        List<OrderDTO> allOrders = orderService.getAllOrderOfItemsWithoutAuthorization();
        return allOrders.stream()
                .filter(orderDTO -> orderDTO.getUserId().equals(id))
                .collect(Collectors.toList());
    }
}
