package com.example.eurder.repositories;

import com.example.eurder.domain.user.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person,Integer> {

    Person findUserByEmailIs(String eMail);

    Optional<Person> findUserByEmail(String email);
}
