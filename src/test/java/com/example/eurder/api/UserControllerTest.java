package com.example.eurder.api;

import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @LocalServerPort
    private int port;


    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void addUserHappyPath() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .body(createAnewUser())
                .when()
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());


    }
    @Test
    void addUserWithoutFirstName() {
        String requestBody = "{\n" +
                "\"firstName\": \"\",\n" +
                "\"lastName\": \"string\",\n" +
                "\"email\": \"str@i.ng\",\n" +
                "\"street\": \"string\",\n" +
                "\"houseNumber\": \"string\",\n" +
                "\"postCode\": \"string\",\n" +
                "\"city\": \"string\",\n" +
                "\"phoneNumber\": \"0123456789\"\n}";

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());


    }
    @Test
    void addUserWithoutLastName() {
        String requestBody = "{\n" +
                "\"firstName\": \"string\",\n" +
                "\"lastName\": \"\",\n" +
                "\"email\": \"str@i.ng\",\n" +
                "\"street\": \"string\",\n" +
                "\"houseNumber\": \"string\",\n" +
                "\"postCode\": \"string\",\n" +
                "\"city\": \"string\",\n" +
                "\"phoneNumber\": \"0123456789\"\n}";

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());


    }




    private static String createAnewUser() {
        String requestBody = "{\n" +
                "\"firstName\": \"string\",\n" +
                "\"lastName\": \"string\",\n" +
                "\"email\": \"str@i.ng\",\n" +
                    "\"street\": \"string\",\n" +
                    "\"houseNumber\": \"string\",\n" +
                    "\"postCode\": \"string\",\n" +
                    "\"city\": \"string\",\n" +
                "\"phoneNumber\": \"0123456789\"\n}";
        return requestBody;
    }




}
