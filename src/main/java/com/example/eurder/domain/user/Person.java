package com.example.eurder.domain.user;

import com.example.eurder.domain.user.Address.Address;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq",allocationSize = 1)
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(unique = true,name = "email")
    private String email;
    @Embedded
    private Address address;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Person(String firstName
            , String lastName, String email
            , Address address, String phoneNumber) {

        this.password = "password";
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER;
    }

    public Person() {

    }

    public Integer getUserId() {
        return id;
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
