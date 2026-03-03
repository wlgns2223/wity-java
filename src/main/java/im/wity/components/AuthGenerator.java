package im.wity.components;

import im.wity.dto.auth.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthGenerator {

    private final JwtProvider jwtProvider;

    public AuthToken generate(String email){
        String accessToken = jwtProvider.createAccessToken(email);
        String refreshToken = jwtProvider.createRefreshToken(email);
        Long accessExpirationSeconds = jwtProvider.getAccessExpirationSeconds();

        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpirationSeconds(accessExpirationSeconds)
                .build();
    }


}
