package com.example.eurder.mapper;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.User;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDtoToUser(UserDto userDto) {
        return new User(userDto.getFirstName()
                , userDto.getFirstName()
                , userDto.getEmail()
                , new Address(userDto.getStreet()
                , userDto.getHouseNumber()
                , userDto.getPostCode()
                , userDto.getCity())
                , userDto.getPhoneNumber());
    }


}
