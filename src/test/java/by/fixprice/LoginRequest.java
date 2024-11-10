package by.fixprice;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static final String LOGIN_URL = "https://api.fix-price.by/buyer/v2/auth/login";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("x-city", "14");
        headers.put("x-key", "740e56af4c394537d535819f54ba29cc");
        return headers;
    }

    public static final String OUTPUT_INVALID_LOGIN_OR_PASSWORD = "Неверный логин или пароль. Проверьте введённые данные и попробуйте снова. Осталось попыток:";
    public static final String OUTPUT_LOGIN_LIMITS_EXCEEDED = "Лимит попыток превышен. Повторите попытку входа через 10 мин. или восстановите пароль.";

    public static String getRandomEmail() {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        return RandomStringUtils.random(5, lowerCase)
                + RandomStringUtils.random(2, upperCase)
                + RandomStringUtils.random(3, numbers)
                + "@gmail.com";
    }

    public static String getRandomPhone() {
        String numbers = "0123456789";
        return "+37529" + RandomStringUtils.random(7, numbers);
    }
}
