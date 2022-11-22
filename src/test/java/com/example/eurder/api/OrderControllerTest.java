package com.example.eurder.api;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Person;
import com.example.eurder.domain.user.Role;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }
    @BeforeAll
    public void createDatabase() {

        Person person = new Person("Jordan", "Soltau", "admin3@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        person.setRole(Role.ADMIN);
        userRepository.save(person);

        Person member1 = new Person("Eva", "Degallaix", "user3@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        userRepository.save(member1);

        Item item = new Item("2", "Mouse", "cliky", 150, 100);
        itemRepository.save(item);

        ItemGroep itemGroep =new ItemGroep(item,1, LocalDate.now(),150);

    }


    @Test
    void addOrderAsMember() {
        Person member = new Person("Eva", "Degallaix", "user5@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        userRepository.save(member);
        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user3@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(orderItem())
                .when()
                .post("orders/"+ member.getUserId())
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
                .basic("user3@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(orderItem())
                .when()
                .post("orders/"+3)
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
                .basic("admin3@eurder.com", "password")
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
                "  \"itemId\": \"2\",\n" +
                "  \"amountToPurchase\": 1\n}";
    }
}