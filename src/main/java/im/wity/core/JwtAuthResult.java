package im.wity.core;

import im.wity.constant.CookieName;
import im.wity.dto.auth.AuthCookie;
import lombok.Builder;

import java.util.List;

@Builder
public class JwtAuthResult implements AuthResult{

    private AuthToken accessToken;
    private AuthToken refreshToken;

    @Override
    public List<AuthCookie> cookies() {
        return List.of(
                AuthCookie.builder()
                        .name(CookieName.ACCESS)
                        .value(accessToken.token())
                        .expirationSeconds(accessToken.expirationInSec() )
                        .build(),
                AuthCookie.builder()
                        .name(CookieName.REFRESH)
                        .value(refreshToken.token())
                        .expirationSeconds(refreshToken.expirationInSec())
                        .build()
        );
    }
}
