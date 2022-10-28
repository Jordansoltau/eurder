package com.example.eurder.dto;

import com.example.eurder.domain.user.Address.Address;

public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;

    public UserDto(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
