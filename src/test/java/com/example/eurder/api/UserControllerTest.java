package com.example.eurder.api;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private ItemRepository itemRepository;

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
                .statusCode(HttpStatus.CREATED.value())
                .extract();

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
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();

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
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();

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