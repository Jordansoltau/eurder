package com.example.eurder.service;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.UserDto;
import com.example.eurder.mapper.UserMapper;
import com.example.eurder.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationInputService.class);
    private final ValidationInputService validationInputService;
    private final SecurityService securityService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ValidationInputService validationInputService, SecurityService securityService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.validationInputService = validationInputService;
        this.securityService = securityService;
        this.userMapper = userMapper;
    }

    public void createANewAccount(UserDto userDto) {
        validationInputService.validateFirstName(userDto, "FirstName");
        validationInputService.validateLastName(userDto, "LastName");
        validationInputService.validateEmail(userDto, "Email");
        validationInputService.validateAdressName(userDto, "Adress");
        validationInputService.validatePhoneNumber(userDto, "phoneNumber");
            userRepository.addNewUser(userMapper.fromDtoToUser(userDto));
    }
}
