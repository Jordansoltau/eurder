package com.example.eurder.api;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Person;
import com.example.eurder.domain.user.Role;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class PersonControllerTest {
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @BeforeAll
    public void createDatabase() {

        Person person = new Person("Jordan", "Soltau", "admin2@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        person.setRole(Role.ADMIN);
        userRepository.save(person);

        Person member = new Person("Eva", "Degallaix", "user2@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        userRepository.save(member);

        Item item = new Item("2", "Mouse", "cliky", 150, 100);
        itemRepository.save(item);
    }

    @Test
    void addUserHappyPath() {

        String requestBody = "{\n" +
                "\"firstName\": \"string\",\n" +
                "\"lastName\": \"String\",\n" +
                "\"email\": \"stringy@i.ng\",\n" +
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
                .basic("admin2@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .body(requestBody)
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
                .basic("admin2@eurder.com", "password")
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
                .basic("admin2@eurder.com", "password")
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
    void getAllUsersAsAdmin() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .auth()
                .preemptive()
                .basic("admin2@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getUsersIdOneAsAdmin() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .auth()
                .preemptive()
                .basic("admin2@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .and()
                .when()
                .get("/users?id="+ 1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

    }


}
