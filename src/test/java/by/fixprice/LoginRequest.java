package by.fixprice;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static final String LOGIN_URL = "https://api.fix-price.by/buyer/v2/auth/login";
    public static final String BODY_NULL_ALL = "{\"password\":null,\"email\":null,\"phone\":null}";
    public static final String BODY_EMPTY_ALL = "{\"password\":\"\",\"email\":\"\",\"phone\":\"\"}";

    private static String numbers = "0123456789";
    private static String gmailDomain = "@gmail.com";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("x-city", "14");
        headers.put("x-key", "740e56af4c394537d535819f54ba29cc");
        return headers;
    }

    public static String getRandomEmail() {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return RandomStringUtils.random(5, lowerCase)
                + RandomStringUtils.random(2, upperCase)
                + RandomStringUtils.random(3, numbers)
                + gmailDomain;
    }

    public static String getRandomPhone() {
        String phoneNumber = "+37529" + RandomStringUtils.random(7, numbers);
        return phoneNumber;
    }
}
