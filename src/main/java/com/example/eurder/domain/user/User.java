package com.example.eurder.domain.user;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Address.Address;
import jdk.dynalink.linker.LinkerServices;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_firstname")
    private String firstName;
    @Column(name = "user_lastname")
    private String lastName;
    @Column(name = "user_email")
    private String email;
    @Embedded
    private Address address;
    @Column(name = "user_phoneNumber")
    private String phoneNumber;
    @Column(name = "user_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    public User(String firstName
            , String lastName, String email
            , Address address, String phoneNumber) {

        this.password = "password";
        userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    public User() {

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
