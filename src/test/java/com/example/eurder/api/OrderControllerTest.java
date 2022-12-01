package com.example.eurder.api;

import com.example.eurder.Repositories.ItemRepository;
import com.example.eurder.Repositories.OrderRepository;
import com.example.eurder.Repositories.ReservedOrderRepository;
import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroep;
import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.ReservedOrder;
import com.example.eurder.domain.user.Address.Address;
import com.example.eurder.domain.user.Person;
import com.example.eurder.domain.user.Role;

import com.example.eurder.repositories.UserRepository;
import com.example.eurder.service.ReservedOrderService;
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


import java.time.LocalDate;


import static io.restassured.RestAssured.given;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class OrderControllerTest {
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReservedOrderRepository reservedOrderRepository;

    @Autowired
    private ReservedOrderService reservedOrderService;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @BeforeAll
    public void createDatabase() {

        Person admin = new Person("Jordan", "Soltau", "admin3@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        Person member1 = new Person("Eva", "Degallaix", "user3@eurder.com", new Address("street", "15", "1540", "brussel"), "0476594874");
        userRepository.save(member1);

        Item item = new Item("2", "Mouse", "cliky", 150, 100);
        itemRepository.save(item);

        ItemGroep itemGroep = new ItemGroep(item, 1, LocalDate.now(), 150);
        ReservedOrder reservedOrder = new ReservedOrder(itemGroep, member1);
        reservedOrderRepository.saveAnOrder(reservedOrder);

        double totalPrice = reservedOrderService.getTotalPrice(member1.getUserId(), listOfAllReservedOrdersOfUser);
        Order order = new Order(member1, totalPrice);
        orderRepository.save(order);
    }


    @Test
    void addOrderAsMember() {
        Person person = userRepository.findUserByEmailIs("user3@eurder.com");

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
                .post("orders/" + person.getUserId())
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
                .post("orders/" + 15)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }


    @Test
    void returnAllOrders() {
        Person person = userRepository.findUserByEmailIs("user3@eurder.com");
        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("user3@eurder.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .when()
                .post("orders/" + person.getUserId() + "/confirm")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

    }

    private String orderItem() {

        return "{\n" +
                "  \"itemId\": \"2\",\n" +
                "  \"amountToPurchase\": 1\n}";
    }
}