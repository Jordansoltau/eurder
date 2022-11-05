package com.example.eurder.api;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.UserRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.user.Feature;
import com.example.eurder.domain.user.Role;
import com.example.eurder.domain.user.User;
import com.example.eurder.dto.ItemDto;
import com.example.eurder.mapper.ItemMapper;
import com.example.eurder.service.ItemService;
import com.example.eurder.service.security.SecurityService;
import com.example.eurder.service.security.UsernamePassword;
import com.example.eurder.service.validation.CustomMessageService;
import com.example.eurder.service.validation.ValidationItemService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Base64;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemControllerTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ItemService itemService;
    @InjectMocks
    private ItemMapper itemMapper;
    @Mock
    private SecurityService securityService = new SecurityService(userRepository);
    @Mock
    private CustomMessageService customMessageService;
    @Mock
    private ValidationItemService validationItemService = new ValidationItemService(userRepository, itemRepository, customMessageService);

    @LocalServerPort
    private int port;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }


    //integration testing
    @Test
    void addItemHappyPath() {
        System.out.println(createAnewItem());
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();
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
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract();
    }


    private static String createAnewItem() {
        String requestBody = "{\n" +
                "  \"name\": \"Mouse\",\n" +
                "  \"description\": 20,\n" +
                "  \"price\": 15 ,\n" +
                "  \"amount\": 5\n}";
        return requestBody;
    }

    //Unit Test
    @Test
    void addItemToItemRepository() {
        ItemDto itemDto = new ItemDto("pc", "description", 100.0, 100);
        String authorization = "YWRtaW5AZXVyZGVyLmNvbTpwYXNzd29yZA==";
        itemService.createANewItemInItemRepository(authorization, itemDto);
        Item item = new Item("148",itemDto.getName(),itemDto.getDescription(),itemDto.getPrice(),itemDto.getAmount());
        System.out.println(item);
        verify(itemRepository).addNewItem(item);
    }

}