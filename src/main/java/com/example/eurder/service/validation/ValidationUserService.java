package com.example.eurder.service.validation;

import com.example.eurder.domain.user.Person;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserService {
    private final CustomMessageService customMessageService;
    private final UserRepository userRepository;

    public ValidationUserService(CustomMessageService customMessageService, UserRepository userRepository) {
        this.customMessageService = customMessageService;

        this.userRepository = userRepository;
    }

    //Users
    public void validateFirstName(UserDto userDto, String firstname) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isBlank())
            throw new IllegalArgumentException(firstname + customMessageService.canNotBeEmptyMessage());
    }

    public void validateLastName(UserDto userDto, String lastName) {
        if (userDto.getLastName() == null || userDto.getLastName().isBlank())
            throw new IllegalArgumentException(lastName + customMessageService.canNotBeEmptyMessage());
    }

    public void validateEmail(UserDto userDto, String email) {
        if (userDto.getEmail() == null
                || userDto.getEmail().isBlank()
                || !userDto.getEmail().matches("^[A-z0-9]+@[A-z0-9]+\\.[A-z0-9]+$"))
            throw new IllegalArgumentException(email + customMessageService.mustBeValid());

        if (emailAlreadyExistInDataBase(userDto)) {
            throw new IllegalArgumentException(email + mustBeUnique());
        }
    }

    private boolean emailAlreadyExistInDataBase(UserDto userDto) {
        return userRepository.findUserByEmailIs(userDto.getEmail()) == null;
    }

    private String mustBeUnique() {
        return "must be unique";
    }

    void validateThatPerson(UserDto userDto, Person person) {
        if (userDto.getEmail().equals(person.getEmail()))
            throw new IllegalArgumentException("E mail is not unique!");

    }

    public void validateAdressName(UserDto userDto, String adress) {
        if (
                userDto.getStreet().isBlank()
                        || userDto.getStreet() == null
                        || userDto.getHouseNumber().isBlank()
                        || userDto.getHouseNumber() == null
                        || userDto.getPostCode().isBlank()
                        || userDto.getPostCode() == null
                        || userDto.getCity().isBlank()
                        || userDto.getCity() == null)
            throw new IllegalArgumentException(adress + customMessageService.canNotBeEmptyMessage());
    }

    public void validatePhoneNumber(UserDto userDto, String phoneNumber) {
        if (userDto.getPhoneNumber() == null
                || userDto.getPhoneNumber().isBlank()
                || userDto.getPhoneNumber().length() != 10)
            throw new IllegalArgumentException(phoneNumber + customMessageService.mustBeValid());
    }

    public void validateNewUser(UserDto userDto) {
        userRepository.findAll().forEach(user -> validateThatPerson(userDto, user));
    }
}
