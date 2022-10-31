package com.example.eurder.Repositories;

import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Role;
import com.example.eurder.domain.user.User;
import com.example.eurder.dto.ItemGroepDto;
import org.springframework.stereotype.Component;
import com.example.eurder.exceptions.NotFoundexception;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserRepository {

    private Map<String, User> usersList;
    private final ItemRepository itemRepository;

    public UserRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.usersList = hardCodedListOfUsers();
    }

    private Map<String, User> hardCodedListOfUsers() {
        User user = new User("John", "Snow", "user@eurder.com", new Address("userStree", "1", "1789", "brussels"), "0476594455");
        User admin = new User("White", "Snow", "admin@eurder.com", new Address("adminStree", "1", "1789", "brussels"), "0476594445");
        admin.setRole(Role.ADMIN);
        HashMap<String, User> hardCodedRepository = new HashMap<>();
        hardCodedRepository.put(user.getUserId(), user);
        hardCodedRepository.put(admin.getUserId(), admin);
        return hardCodedRepository;
    }


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




}

