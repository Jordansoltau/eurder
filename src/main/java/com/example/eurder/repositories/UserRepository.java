package com.example.eurder.repositories;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Role;
import com.example.eurder.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.example.eurder.exceptions.NotFoundexception;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User,String> {



    public Collection<User> getAllPersons() {
        return usersList.values();
    }

    public User getUserByEmailForLogin(String eMail) {

        return usersList.values().stream().filter(person -> person.getEmail().equals(eMail)).findFirst().orElseThrow(NotFoundexception::new);
    }


    public void addNewUser(User user) {
        usersList.put(user.getUserId(), user);
    }

    public boolean doesEmailAlreadyExist(String email) {
        return usersList.containsValue(email);
    }


    public User getUserById(String id) {
        return usersList.get(id);
    }
}

