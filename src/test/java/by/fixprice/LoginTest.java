package by.fixprice;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTest {
    @BeforeEach
    public void waitBeforeTest() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
                .body("message", equalTo("Ошибка валидации"))
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
                .body("message", equalTo("Ошибка валидации"))
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
                .body("message", equalTo("Ошибка валидации"))
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
                .body("message", equalTo("Ошибка валидации"))
                .body("extra.phone[0]", equalTo("Требуется указать телефон"));
    }

    @Test
    @DisplayName("Empty password + phone")
    public void emptyPasswordTest() {
        String randomPhone = LoginRequest.getRandomPhone();
        given()
                .body("{\"password\":\"\",\"email\":null,\"phone\":\"" + randomPhone + "\"}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Ошибка валидации"))
                .body("extra.password[0]", equalTo("Требуется указать пароль"));
    }

    @Test
    @DisplayName("Email only (+ null password)")
    public void emailOnlyTest() {
        String randomEmail = LoginRequest.getRandomEmail();
        given()
                .body("{\"password\":null,\"email\":\"" + randomEmail + "\",\"phone\":null}")
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Ошибка валидации"))
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
                .body("message", equalTo("Ошибка валидации"))
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
                .body("message", equalTo("Ошибка валидации"))
                .body("extra.phone[0]", equalTo("Укажите корректный номер телефона"));
    }

    @Test
    @DisplayName("Invalid phone + invalid password")
    public void invalidPhoneAndPasswordTest() {
        String randomPhone = LoginRequest.getRandomPhone();
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":null,\"phone\":\"" + randomPhone + "\"}")
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
        String randomEmail = LoginRequest.getRandomEmail();
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"" + randomEmail + "\",\"phone\":null}")
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
        String randomEmail = LoginRequest.getRandomEmail();
        String randomPhone = LoginRequest.getRandomPhone();
        given()
                .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"" + randomEmail + "\",\"phone\":\"" + randomPhone + "\"}")
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

    @Test
    @DisplayName("No header x-city")
    public void noHeaderXCityTest() {
        given()
                .body("{\"password\":null,\"email\":\"sushihryushi@banan.kek\",\"phone\":null}")
                .header("x-key", "740e56af4c394537d535819f54ba29cc")
                .contentType("application/json")
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Функционал недоступен. Пожалуйста, обновите приложение"));
    }

    @Test
    @DisplayName("No header contentType")
    public void noHeaderContentTypeTest() {
        given()
                .body("{\"password\":\"\",\"email\":\"sushihryushi@banan.kek\",\"phone\":\"+375298888888\"}")
                .header("x-key", "740e56af4c394537d535819f54ba29cc")
                .header("x-city", "14")
                .when()
                .post(LoginRequest.LOGIN_URL)
                .then()
                .statusCode(400)
                .log().all()
                .body("message", equalTo("Ошибка валидации"))
                .body("extra.phone[0]", equalTo("Требуется указать телефон или email"));
    }

    @Test
    @DisplayName("Too many requests")
    public void tooManyRequestsTest() {
        boolean hasTooManyRequestsResponse = false;
        String randomPhone = LoginRequest.getRandomPhone();

        while (!hasTooManyRequestsResponse) {
            Response response = given()
                    .body("{\"password\":\"johnsonsbaby24/7\",\"email\":null,\"phone\":\"" + randomPhone + "\"}")
                    .headers(LoginRequest.getHeaders())
                    .when()
                    .post(LoginRequest.LOGIN_URL);

            if (response.statusCode() == 400) {
                String message = response.then().extract().path("message");
                if (message.equals("Слишком много запросов")) {
                    hasTooManyRequestsResponse = true;
                }
            }
        }

        Assertions.assertTrue(hasTooManyRequestsResponse);
    }

    @Test
    @DisplayName(" more 5 tempts failed")
    public void sendFiveTriesTest() {
        boolean hasFiveRequestsResponse = false;
        String randomEmail = LoginRequest.getRandomEmail();

        for (int i = 1; i < 6; i++) {
            Response response = given()
                    .body("{\"password\":\"johnsonsbaby24/7\",\"email\":\"" + randomEmail + "\",\"phone\":null}")
                    .headers(LoginRequest.getHeaders())
                    .when()
                    .post(LoginRequest.LOGIN_URL);

            if (response.statusCode() == 400) {
                String message = response.then().extract().path("message"); //System.out.println(message); for checking
                if (message.contains(LoginRequest.OUTPUT_LOGIN_LIMITS_EXCEEDED)) {
                    hasFiveRequestsResponse = true;
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            //System.out.println(" " + i + " " + hasFiveRequestsResponse); for checking
        }

        Assertions.assertTrue(hasFiveRequestsResponse);
    }
}
