package im.wity.dto.auth;


import lombok.Builder;

@Builder
public record AuthToken(String accessToken,String refreshToken,Long accessExpirationSeconds) {
}
