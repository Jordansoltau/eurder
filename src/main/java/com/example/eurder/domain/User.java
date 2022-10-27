package com.example.eurder.domain;

import com.example.eurder.domain.Address.Address;
import java.util.UUID;

public class User {
    private final String UserId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;
    private final Role role;

    public User(String firstName, String lastName, String email, Address address, String phoneNumber) {
        UserId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.USER;
    }

    public String getUserId() {
        return UserId;
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

    public Role getRole() {
        return role;
    }
}
