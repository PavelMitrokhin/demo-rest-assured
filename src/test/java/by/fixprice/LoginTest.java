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
                .header("Content-Type", "application/json")
                .header("x-city", "14")
                .header("x-key", "740e56af4c394537d535819f54ba29cc")
                .when()
                .post("https://api.fix-price.by/buyer/v2/auth/login")
                .then()
                .statusCode(400)
                .log().all()
                .body("extra.phone[0]", equalTo("Требуется указать телефон или email"));
    }
}
