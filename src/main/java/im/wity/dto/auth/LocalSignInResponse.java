package im.wity.dto.auth;


import im.wity.dto.user.UserResponse;
import im.wity.entity.User;
import lombok.Builder;

@Builder
public record LocalSignInResponse(String accessToken, String refreshToken, long accessExpirationSeconds, UserResponse user) {
    public static LocalSignInResponse from(AuthToken authToken,User user){
        return LocalSignInResponse.builder()
                .accessToken(authToken.accessToken())
                .refreshToken(authToken.refreshToken())
                .accessExpirationSeconds(authToken.accessExpirationSeconds())
                .user(UserResponse.from(user))
                .build();
    }
}
