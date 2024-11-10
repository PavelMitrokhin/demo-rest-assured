package by.fixprice;

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
}
