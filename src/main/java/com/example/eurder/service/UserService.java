package com.example.eurder.service;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.UserDto;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.validation.ValidationItemService;
import com.example.eurder.service.validation.ValidationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationItemService.class);
    private final ValidationUserService validationUserService;
    private final SecurityService securityService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ValidationUserService validationUserService, SecurityService securityService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.validationUserService = validationUserService;
        this.securityService = securityService;
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
}
