package by.bypet;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static final String URL_LOGIN = "https://bypet.by/auth/login";

    public static String getBody(String phoneStripped) {
        return "{\n" +
                "    \"phone_stripped\": \"" + phoneStripped + "\",\n" +
                "    \"phone\": \"+375 (29) 333-33-33\",\n" +
                "    \"password\": \"ergaer\"\n" +
                "}";
    }
}
