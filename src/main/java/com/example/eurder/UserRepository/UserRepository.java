package com.example.eurder.UserRepository;

import com.example.eurder.domain.Address.Address;
import com.example.eurder.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private Map<String, User> usersList;

    public UserRepository() {
        this.usersList = hardCodedListOfUsers();
    }

    private Map<String, User> hardCodedListOfUsers() {
        User user = new User("John", "Snow", "user@eurder.com", new Address("userStree","1","1789","brussels"),"0476594455");
        User admin = new User("White", "Snow", "admin@eurder.com", new Address("adminStree","1","1789","brussels"),"0476594445");
        HashMap<String,User> hardCodedRepository = new HashMap<>();
        hardCodedRepository.put(user.getUserId(),user);
        hardCodedRepository.put(admin.getUserId(),admin);
        return hardCodedRepository;
    }


}
