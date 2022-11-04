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
    private final ArrayList<ItemGroep> cart;
    private final ArrayList<ItemGroep> pastOrders;

    public User(String id, String firstName
            , String lastName, String email
            , Address address, String phoneNumber) {

        this.cart = new ArrayList<>();
        this.password = "password";
        this.userId = id;
//        userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        this.role = Role.CUSTOMER;
        pastOrders = new ArrayList<>();
    }

    public ArrayList<ItemGroep> getCurrentOrder() {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Can not confirm order");
        }
        ArrayList<ItemGroep> confirmedCart = copyCartToPastOrder();

        moveToPastOrders();
        return confirmedCart;
    }

    private ArrayList<ItemGroep> copyCartToPastOrder() {
        return new ArrayList<>(cart);
    }

    private void moveToPastOrders() {
        pastOrders.addAll(cart);
        cart.clear();
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

    public void addToCart(ItemGroep itemGroep) {
        cart.add(itemGroep);
    }

    public void confirmCart() {

    }
}
