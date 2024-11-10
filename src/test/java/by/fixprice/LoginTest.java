package by.fixprice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
    @DisplayName("Empty password + phone")
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

    @Test
    @DisplayName("Email only (+ null password)")
    public void emailOnlyTest() {
        given()
                .body("{\"password\":null,\"email\":\"sushihryushi@banan.kek\",\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.password[0]", equalTo("Требуется указать пароль"));
    }

    @Test
    @DisplayName("Incorrect email")
    public void incorrectEmailTest() {
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"sushihryushi\",\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.email[0]", equalTo("Укажите корректный email"));
    }

    @Test
    @DisplayName("Incorrect phone")
    public void incorrectPhoneTest() {
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":null,\"phone\":\"sushihryushi@banan.kek\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.phone[0]", equalTo("Укажите корректный номер телефона"));
    }

    @Test
    @DisplayName("Invalid phone + invalid password")
    public void invalidPhoneAndPasswordTest() {
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":null,\"phone\":\"+375777777777\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", containsString(LoginRequest.OUTPUT_INVALID_LOGIN_OR_PASSWORD));
    }

    @Test
    @DisplayName("Invalid email + invalid password")
    public void invalidEmailAndPasswordTest() {
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"sushihryushi@gmail.com\",\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", containsString(LoginRequest.OUTPUT_INVALID_LOGIN_OR_PASSWORD));
    }

    @Test
    @DisplayName("Email + phone + password")
    public void sendEmailAndPhoneAndPasswordTest() {
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"sushihryushi@gmail.com\",\"phone\":\"+375298888888\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", containsString(LoginRequest.OUTPUT_INVALID_LOGIN_OR_PASSWORD));
    }

    @Test
    @DisplayName("No headers")
    public void noHeadersTest() {
        given()
                .body("{\"password\":null,\"email\":\"sushihryushi@banan.kek\",\"phone\":null}")
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Функционал недоступен. Пожалуйста, обновите приложение"));
    }

    @Test
    @DisplayName("No header x-key")
    public void noHeaderXKeyTest() {
        given()
                .body("{\"password\":null,\"email\":\"sushihryushi@banan.kek\",\"phone\":null}")
                .header("x-city", "14")
                .contentType("application/json")
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Ошибка валидации"));
    }
}
