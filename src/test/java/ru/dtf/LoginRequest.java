package ru.dtf;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static final String URL_LOGIN = "https://api.dtf.ru/v3.4/auth/email/login";

    public static String getBody(String phoneStripped) {
        return "email:test@test.com\n" +
                "password:1243124321";
    }

    public static Map<String, String> getFormData() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("email", "test@test.com");
        body.put("password", "1243124321");
        return body;
    }
}
