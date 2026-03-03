package im.wity.components;

import im.wity.core.AuthManager;
import im.wity.core.AuthResult;
import im.wity.core.AuthToken;
import im.wity.core.JwtAuthResult;
import im.wity.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthManager implements AuthManager {

    private final JwtProvider jwtProvider;

    @Override
    public AuthResult process(User user) {
        AuthToken accessToken = jwtProvider.createAccessToken(user.getEmail());
        AuthToken refreshToken = jwtProvider.createRefreshToken(user.getEmail());

        return JwtAuthResult.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
