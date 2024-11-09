package by.tabletka;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest {
    public static final String URL_LOGIN = "https://tabletka.by/ajax-request/login";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Cookie", "regionId=0; PHPSESSID=3a1f8lf9n4e5j0953k65ogcieq; _csrf=4c41d0c0c668c8d0564ac68e1ab66d5e8f17248b2b485b667dc6a883a208fdeaa%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22RB1qXHb5Bkh0Fo0DFjUcTGmPhZZRUdHa%22%3B%7D; _ga=GA1.1.44820776.1730998034; __gads=ID=02290ac70a9c3eb3:T=1730998031:RT=1730998031:S=ALNI_MbcZz52BTSek-Riq2P4lXCwHahpNA; __eoi=ID=f484fadd6072ebd3:T=1730998031:RT=1730998031:S=AA-AfjaQV4sLOqMVUaS1R4-0j5uH; _ga_S6LL4MRH46=GS1.1.1730998033.1.1.1730998046.0.0.0");
        headers.put("X-Requested-With","XMLHttpRequest");
        return headers;
    }

    public static String getBody() {
        return "_csrf=g5jePrUgWAiZMfzvWndMOkVar9MwNhs1vGSBB77GcZ_R2u9P7Wg6PdtalN8cGHx-AzD6sGRxdmXUPttV66I5_g%3D%3D&email=weyru%40weo.rwe&password=ogwjowgjo&rememberMe=on";
    }
}
