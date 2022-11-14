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
import static org.mockito.Mockito.verify;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemControllerTest {

    @LocalServerPort
    private int port;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
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
                .patch("/items/10")
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