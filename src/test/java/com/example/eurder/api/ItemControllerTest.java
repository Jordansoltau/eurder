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
class ItemControllerTest {
    @Autowired
    UserRepository userRepository;

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

        Person person = new Person("Jordan", "Soltau", "admin@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        person.setRole(Role.ADMIN);
        userRepository.save(person);

        Person member = new Person("Eva", "Degallaix", "user@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        userRepository.save(member);

        Item item = new Item("1","Mouse","cliky",150,100);
        itemRepository.save(item);
    }

    //integration testing
    @Test
    void addItemHappyPath() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(createAnewItem())
                .when()
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void addItemAsMember() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(createAnewItem())
                .when()
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void addItemEmailDoesNotExist() {
        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("adn@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(createAnewItem())
                .when()
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void updateItemHappyPath() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(createAnewItem())
                .when()
                .patch("/items/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void itemOverview() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("/items/stock")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }


    private static String createAnewItem() {
        String requestBody = "{\n" +
                "  \"name\": \"Mouse\",\n" +
                "  \"description\": 20,\n" +
                "  \"price\": 15 ,\n" +
                "  \"amount\": 5\n}";
        return requestBody;
    }


}