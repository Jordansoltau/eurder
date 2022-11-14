package com.example.eurder.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {
    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }


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
                .post("orders/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void addOrderAsMemberOnOtherMemberId() {

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
                .post("orders/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }



    @Test
    void returnAllOrders() {
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
                .get("orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

    }

    private String orderItem() {

        return "{\n" +
                "  \"itemId\": \"10\",\n" +
                "  \"amountToPurchase\": 1\n}";
    }
}