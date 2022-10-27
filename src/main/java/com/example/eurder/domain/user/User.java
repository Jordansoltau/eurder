package com.example.eurder.domain.user;

import com.example.eurder.domain.user.Address.Address;
import java.util.UUID;

public class User {
    private final String UserId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;
    private final String password;
    private Role role;

    public User(String firstName, String lastName, String email, Address address, String phoneNumber, String password) {
        this.password = password;
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
    public boolean canHaveAccessTo(Feature feature){
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