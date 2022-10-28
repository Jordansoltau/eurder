package com.example.eurder.service.security;

import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.domain.user.User;
import com.example.eurder.exceptions.UnauthorizatedException;
import com.example.eurder.exceptions.UnknownPersonException;
import com.example.eurder.exceptions.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserRepository personRepository;

    public SecurityService(UserRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);
        User user = personRepository.getUserByEmailForLogin(usernamePassword.getUsername());


        if (user == null) {
            logger.error("Unknown user with the username " + usernamePassword.getUsername());
            throw new UnknownPersonException();
        }
        if (!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUsername());
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error("User " + user.getFullName() + " does not have access to " + feature);
            throw new UnauthorizatedException();
        }

    }

    public String getEmail(String auth) {
        return getUsernamePassword(auth).getUsername();
    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}
