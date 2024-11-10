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
}
