package by.tabletka;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    @Test
    @Description("Code 200")
    public void test1() {
        given()
                .body(LoginRequest.getBody())
                .headers(LoginRequest.getHeaders())
        .when()
                .post(LoginRequest.URL_LOGIN)
        .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(-1))
                .body("log", equalTo("Не найден пользователь или неправильный пароль."));
    }

    @Test
    public void test2() {
        String body = "_csrf=g5jePrUgWAiZMfzvWndMOkVar9MwNhs1vGSBB77GcZ_R2u9P7Wg6PdtalN8cGHx-AzD6sGRxdmXUPttV66I5_g%3D%3D\n" +
                "&\n" +
                "email=" +
                "&\n" +
                "password=123\n" +
                "&\n" +
                "rememberMe=on";
        String url = "https://tabletka.by/ajax-request/login";
        //String bodyResponse = "";

        given()
                .body(body)
                .headers(LoginRequest.getHeaders())

                .when()
                .post(LoginRequest.URL_LOGIN)

                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(-1))
                .body("log", equalTo("Не найден пользователь или неправильный пароль."));
    }

    @Test
    public void test3() {
        String body = "_csrf=g5jePrUgWAiZMfzvWndMOkVar9MwNhs1vGSBB77GcZ_R2u9P7Wg6PdtalN8cGHx-AzD6sGRxdmXUPttV66I5_g%3D%3D\n" +
                "&\n" +
                "email=123\n" +
                "&\n" +
                "password=" +
                "&\n" +
                "rememberMe=on";

        given()
                .body(body)
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.URL_LOGIN)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(-1))
                .body("log", equalTo("Не найден пользователь или неправильный пароль."));
    }

    @Test
    public void test4() {
        String body = "_csrf=g5jePrUgWAiZMfzvWndMOkVar9MwNhs1vGSBB77GcZ_R2u9P7Wg6PdtalN8cGHx-AzD6sGRxdmXUPttV66I5_g%3D%3D\n" +
                "&\n" +
                "email=123\n" +
                "&\n" +
                "password=123\n" +
                "&\n" +
                "rememberMe=on";

        given()
                .body(body)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Cookie", "regionId=0; PHPSESSID=3a1f8lf9n4e5j0953k65ogcieq; _csrf=4c41d0c0c668c8d0564ac68e1ab66d5e8f17248b2b485b667dc6a883a208fdeaa%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22RB1qXHb5Bkh0Fo0DFjUcTGmPhZZRUdHa%22%3B%7D; _ga=GA1.1.44820776.1730998034; __gads=ID=02290ac70a9c3eb3:T=1730998031:RT=1730998031:S=ALNI_MbcZz52BTSek-Riq2P4lXCwHahpNA; __eoi=ID=f484fadd6072ebd3:T=1730998031:RT=1730998031:S=AA-AfjaQV4sLOqMVUaS1R4-0j5uH; _ga_S6LL4MRH46=GS1.1.1730998033.1.1.1730998046.0.0.0")
                .when()
                .post(LoginRequest.URL_LOGIN)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(-1));
    }

    @Test
    public void test5() {
        String body = "_csrf=g5jePrUgWAiZMfzvWndMOkVar9MwNhs1vGSBB77GcZ_R2u9P7Wg6PdtalN8cGHx-AzD6sGRxdmXUPttV66I5_g%3D%3D\n" +
                "&\n" +
                "email=" +
                "&\n" +
                "password=" +
                "&\n" +
                "rememberMe=on";

        given()
                .body(body)
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.URL_LOGIN)
                .then()
                .log().all()
                .statusCode(200)
                .body("status", equalTo(-1))
                .body("log", equalTo("Не найден пользователь или неправильный пароль."));
    }

    @Test
    public void test6(){
        String body = "email=123\n" +
                "&\n" +
                "password=123\n" +
                "&\n" +
                "rememberMe=on";

        given()
                .body(body)
                .headers(LoginRequest.getHeaders())
                .when()
                .post(LoginRequest.URL_LOGIN)
                .then()
                .log().all()
                .statusCode(400)
                .body(equalTo("Bad Request (#400): Unable to verify your data submission."));
    }
}
