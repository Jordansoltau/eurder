package com.example.eurder.mapper;

import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Person;
import com.example.eurder.service.dto.personDto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Person fromDtoToUser(UserDto userDto) {

        return new Person(
                userDto.getFirstName()
                , userDto.getFirstName()
                , userDto.getEmail()
                , new Address(userDto.getStreet()
                , userDto.getHouseNumber()
                , userDto.getPostCode()
                , userDto.getCity())
                , userDto.getPhoneNumber());
    }


}
