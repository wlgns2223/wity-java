package im.wity.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class CookieTokenExtractor {

    private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    private CookieTokenExtractor(){}

    public static Optional<String> extract(HttpServletRequest request){
        if(request.getCookies() == null) return Optional.empty();

        return Arrays.stream(request.getCookies())
                .filter(c -> ACCESS_TOKEN_COOKIE_NAME.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

}
