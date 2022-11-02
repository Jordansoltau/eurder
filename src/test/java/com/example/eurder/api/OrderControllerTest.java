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
class OrderControllerTest {
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
    void getAllOrderAsMemberWhenListEmpty() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(orderItem())
                .when()
                .get("/orders/1" )
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
    }
//    @Test
//    void getAllOrderAsMemberAfterOrder() {
//
//        given()
//                .baseUri("http://localhost")
//                .port(port)
//                .auth()
//                .preemptive()
//                .basic("user@eurder.com", "password")
//                .header("Accept", ContentType.JSON.getAcceptHeader())
//                .header("Content-type", "application/json")
//                .and()
//                .when()
//                .get("/orders/15" )
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract();
//    }
    @Test
    void addOrderAsMember() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(orderItem())
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    private String orderItem() {

        return "{\n" +
                "  \"itemId\": \"10\",\n" +
                "  \"amountToPurchase\": 1\n}";
    }

}