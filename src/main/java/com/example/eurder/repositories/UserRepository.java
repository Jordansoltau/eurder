package com.example.eurder.repositories;

import com.example.eurder.domain.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<Person,String> {
    

     Person findUserByEmailIs(String eMail);

     Optional<Person> findUserByEmail(String email);

}

