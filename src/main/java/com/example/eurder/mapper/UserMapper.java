package com.example.eurder.mapper;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.User;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User fromDtoToUser(UserDto userDto) {
        String randomId = UUID.randomUUID().toString();
        return new User(randomId,
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
