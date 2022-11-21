package com.example.eurder.service.security;

import com.example.eurder.domain.user.Person;
import com.example.eurder.exceptions.NotFoundexception;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.exceptions.UnauthorizatedException;
import com.example.eurder.exceptions.UnknownPersonException;
import com.example.eurder.exceptions.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        Person person = userRepository.findUserByEmail(usernamePassword.getUsername()).orElseThrow(()-> new NotFoundexception());


        if (person == null) {
            logger.error("Unknown user with the username " + usernamePassword.getUsername());
            throw new UnknownPersonException();
        }
        if (!person.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if (!person.canHaveAccessTo(feature)) {
            logger.error("User " + person.getFullName() + " does not have access to " + feature);
            throw new UnauthorizatedException();
        }

    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }



    public void validateUserAndAuthorization(String authorization, String userId) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        Person person = userRepository.findUserByEmail(usernamePassword.getUsername()).orElseThrow();
        if (!Objects.equals(person.getUserId(), userId)) {
            throw new IllegalArgumentException("You do not have access");
        }

    }

}
