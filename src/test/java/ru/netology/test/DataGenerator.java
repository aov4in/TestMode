package ru.netology.test;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator(){}

    private static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static void makeRegistration(RegUser registration) {
        given()
                .spec(requestSpec)
                .body(registration)

                .when()
                .post("/api/system/users")

                .then()
                .statusCode(200);
    }

    public static RegUser generateNewActiveUser() {
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        makeRegistration(new RegUser(login, password, "active"));
        return new RegUser(login, password, "active");
    }

    public static RegUser generateNewBlockedUser() {
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        makeRegistration(new RegUser(login, password, "blocked"));
        return new RegUser(login, password, "blocked");
    }

    public static RegUser generateNewUserWithInvalidLogin() {
        String password = faker.internet().password();
        String status = "active";
        makeRegistration(new RegUser("vasya", password, status));
        return new RegUser("ivan", password, status);
    }

    public static RegUser generateNewUserWithInvalidPassword() {
        String login = faker.name().firstName().toLowerCase();
        String status = "active";
        makeRegistration(new RegUser(login, "password", status));
        return new RegUser(login, "qwerty", status);
    }
}
