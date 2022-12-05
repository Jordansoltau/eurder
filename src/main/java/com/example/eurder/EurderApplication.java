package com.example.eurder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EurderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurderApplication.class, args);
    }
//    @Bean
//    public Keycloak keycloak(@Value("${master.keycloak.username}") String adminUsername, @Value("${master.keycloak.password}") String adminPassword) {
//        return KeycloakBuilder.builder()
//                .serverUrl("https://keycloak.switchfully.com/auth")
//                .grantType(OAuth2Constants.PASSWORD)
//                //TODO: check with Christophe if this is correct or should be realm: parkShark-babyshark, clientId: parkShark
//                .realm("master")
//                .clientId("admin-cli")
//                .username(adminUsername)
//                .password(adminPassword)
//                .resteasyClient(
//                        new ResteasyClientBuilder()
//                                .connectionPoolSize(10).build()
//                )
//                .build();
//    }
//
//    @Bean
//    public KeycloakConfigResolver KeycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }


}
