package im.wity.dto;


import im.wity.entity.User;
import lombok.Builder;

@Builder
public record LocalSignInResponse(String accessToken, String refreshToken, long accessExpirationSeconds, User user) { }
