package com.example.eurder.api;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.user.Role;
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
class ItemControllerTest {
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
    void addItemHappyPath(){
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
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    void addItemAsMember(){
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();
    }
@Test
    void addItemEmailDoesNotExist(){
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
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract();
    }

    private static String createAnewItem() {
        String requestBody = "{\n" +
                "  \"itemGroepId\": \"Mouse\",\n" +
                "  \"amount\": 20,\n" +
                "  \"amount\": 15 \n}"
                ;
        return requestBody;
    }

    @Test
    void addOrderAsMember(){
        System.out.println(orderAnewItem());
        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(orderAnewItem())
                .when()
                .post("/items/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    private String orderAnewItem() {
        {
            return "{\n"+
                    "\"itemId\": \"string\",\n"+
                    "\"amount\": 1,\n"+
                    "\"shipingDate\": \"2022-10-28\",\n"+
                    "\"itemRepository\": {},\n"+
                    "\"dateDependingOnStock\": \"2022-10-28\"\n}";
        }
    }
}