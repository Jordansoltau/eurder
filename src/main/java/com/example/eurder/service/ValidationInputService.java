package com.example.eurder.service;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationInputService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(ValidationInputService.class);

    public ValidationInputService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validateAmountOfitemDto(ItemDto itemDto, String amount) {
        if (itemDto.getAmount() < 1) throw new IllegalArgumentException(amount + mustBeHigherThanZeroMessage());
    }

    public void validatePriceOfitemDto(ItemDto itemDto, String price) {
        if (itemDto.getPrice() < 1) throw new IllegalArgumentException(price + mustBeHigherThanZeroMessage());
    }

    public void validateDescriptionOfitemDto(ItemDto itemDto, String description) {
        if (itemDto.getDescription() == null || itemDto.getDescription().isBlank())
            throw new IllegalArgumentException(description + canNotBeEmptyMessage());
    }

    public void validateNameOfitemDto(ItemDto itemDto, String name) {
        if (itemDto.getName() == null || itemDto.getName().isBlank())
            throw new IllegalArgumentException(name + canNotBeEmptyMessage());
    }


    //Users
    public void validateFirstName(UserDto userDto, String firstname) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isBlank())
            throw new IllegalArgumentException(firstname + canNotBeEmptyMessage());
    }

    public void validateLastName(UserDto userDto, String lastName) {
        if (userDto.getLastName() == null || userDto.getLastName().isBlank())
            throw new IllegalArgumentException(lastName + canNotBeEmptyMessage());
    }

    public void validateEmail(UserDto userDto, String email) {

        if (userDto.getEmail() == null || userRepository.doesEmailAlreadyExist(userDto.getEmail()))
            throw new IllegalArgumentException(email + mustBeValid());
    }

    public void validateAdressName(UserDto userDto, String adress) {
        if (userDto.getAddress() == null
                || userDto.getAddress().getStreet().isBlank()
                || userDto.getAddress().getStreet()==null
                || userDto.getAddress().getHouseNumber().isBlank()
                || userDto.getAddress().getHouseNumber()==null
                || userDto.getAddress().getCity().isBlank()
                || userDto.getAddress().getCity()==null
                || userDto.getAddress().getPostCode().isBlank()
                || userDto.getAddress().getPostCode()==null)
            throw new IllegalArgumentException(adress + canNotBeEmptyMessage());
    }

    public void validatePhoneNumber(UserDto userDto, String phoneNumber) {
        if (userDto.getPhoneNumber()==null
        ||userDto.getPhoneNumber().isBlank()
        ||userDto.getPhoneNumber().length() != 10)
            throw new IllegalArgumentException(phoneNumber + mustBeValid());
    }

    private static String mustBeValid() {
        return "must be valid";
    }

    private String mustBeHigherThanZeroMessage() {
        return " must be higher than Zero!";
    }

    private String canNotBeEmptyMessage() {
        return " can not be empty!";
    }
}
