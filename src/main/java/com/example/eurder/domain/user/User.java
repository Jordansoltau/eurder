package com.example.eurder.domain.user;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Address.Address;
import jdk.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;
    private final String password;
    private Role role;

    public User(String id, String firstName
            , String lastName, String email
            , Address address, String phoneNumber) {

        this.password = "password";
        this.userId = id;
//        userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        this.role = Role.CUSTOMER;

    }

    public String getUserId() {
        return userId;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return getRole().containsFeature(feature);
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

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }


}
