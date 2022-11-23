package com.example.eurder.service.validation;

import com.example.eurder.domain.user.Person;
import com.example.eurder.dto.UserDto;
import com.example.eurder.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserService {
    private final CustomMessageService customMessageService;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationUserService.class);

    public ValidationUserService(CustomMessageService customMessageService, UserRepository userRepository) {
        this.customMessageService = customMessageService;
        this.userRepository = userRepository;
    }

    //Users
    public void validateFirstName(UserDto userDto, String firstname) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isBlank())
            throw new IllegalArgumentException(firstname + customMessageService.canNotBeEmptyMessage());
        logger.info("Description " + customMessageService.canNotBeEmptyMessage());
    }

    public void validateLastName(UserDto userDto, String lastName) {
        if (userDto.getLastName() == null || userDto.getLastName().isBlank())
            throw new IllegalArgumentException(lastName + customMessageService.canNotBeEmptyMessage());
        logger.info("Lastname " + customMessageService.canNotBeEmptyMessage());
    }

    public void validateEmail(UserDto userDto, String email) {
        if (userDto.getEmail() == null
                || userDto.getEmail().isBlank()
                || !userDto.getEmail().matches("^[A-z0-9]+@[A-z0-9]+\\.[A-z0-9]+$")) {
            logger.info("email  " + customMessageService.mustBeValid());
            throw new IllegalArgumentException(email + customMessageService.mustBeValid());
        }
        if (emailAlreadyExistInDataBase(userDto)) {
            logger.info("email  " + customMessageService.mustBeUnique());
            throw new IllegalArgumentException(email + customMessageService.mustBeUnique());
        }
    }

    private boolean emailAlreadyExistInDataBase(UserDto userDto) {
        return userRepository.findUserByEmailIs(userDto.getEmail()) != null;
    }


    void validateThatPerson(UserDto userDto, Person person) {
        if (userDto.getEmail().equals(person.getEmail()))
            logger.info("email  " + customMessageService.mustBeUnique());
        throw new IllegalArgumentException("E mail is not unique!");

    }

    public void validateAddressName(UserDto userDto, String address) {
        if (
                userDto.getStreet().isBlank()
                        || userDto.getStreet() == null
                        || userDto.getHouseNumber().isBlank()
                        || userDto.getHouseNumber() == null
                        || userDto.getPostCode().isBlank()
                        || userDto.getPostCode() == null
                        || userDto.getCity().isBlank()
                        || userDto.getCity() == null)
            logger.info("address  " + customMessageService.canNotBeEmptyMessage());
        throw new IllegalArgumentException(address + customMessageService.canNotBeEmptyMessage());
    }

    public void validatePhoneNumber(UserDto userDto, String phoneNumber) {
        if (userDto.getPhoneNumber() == null
                || userDto.getPhoneNumber().isBlank()
                || userDto.getPhoneNumber().length() != 10)
            logger.info("phonenumber  " + customMessageService.mustBeValid());
        throw new IllegalArgumentException(phoneNumber + customMessageService.mustBeValid());
    }

    public void validateNewUser(UserDto userDto) {
        userRepository.findAll().forEach(user -> validateThatPerson(userDto, user));
    }
}
