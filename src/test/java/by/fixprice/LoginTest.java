package by.fixprice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    @Test
    @DisplayName("Null credentials: email, phone and password")
    public void nullCredentialsTest() {
        given()
                .body("{\"password\":null,\"email\":null,\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.phone[0]", equalTo("Требуется указать телефон или email"));
    }

    @Test
    @DisplayName("Empty (\"\") credentials: email, phone and password")
    public void emptyCredentialsTest() {
        given()
                .body("{\"password\":\"\",\"email\":\"\",\"phone\":\"\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.password[0]", equalTo("Требуется указать пароль"));
    }

    @Test
    @DisplayName("Empty email")
    public void emptyEmailTest() {
        given()
                .body("{\"password\":\"\",\"email\":\"\",\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.email[0]", equalTo("Требуется указать email"));
    }

    @Test
    @DisplayName("Empty phone")
    public void emptyPhoneTest() {
        given()
                .body("{\"password\":\"\",\"email\":null,\"phone\":\"\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.phone[0]", equalTo("Требуется указать телефон"));
    }

    @Test
    @DisplayName("Empty password")
    public void emptyPasswordTest() {
        given()
                .body("{\"password\":\"\",\"email\":null,\"phone\":\"+375 (29) 999-99-99\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.password[0]", equalTo("Требуется указать пароль"));
    }
}
