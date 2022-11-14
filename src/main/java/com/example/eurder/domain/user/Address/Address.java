package com.example.eurder.domain.user.Address;

import javax.persistence.*;


@Embeddable
public class Address {

    @Column(name = "street")
    private String street;
    @Column(name = "housenumber")
    private String houseNumber;
    @Column(name = "postcode")
    private String postCode;
    @Column(name = "city")
    private String city;

    public Address() {

    }

    public Address(String street, String houseNumber, String postCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
