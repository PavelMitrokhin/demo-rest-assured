package by.bypet;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    @Test
    public void test1() {
        given()
                .body(by.bypet.LoginRequest.getBody(""))
                .when()
                .post(LoginRequest.URL_LOGIN)
                .then()
                .log().all()
                .statusCode(422)
                .body("errors.phone_stripped[0]", equalTo("The phone stripped field is required."))
                .body("errors.password[0]", equalTo("The password field is required."));
    }
}
