package im.wity.dto.auth;


import im.wity.constant.CookieName;
import lombok.Builder;

@Builder
public record AuthCookie(CookieName name, String value, Long expirationSeconds) {
}
