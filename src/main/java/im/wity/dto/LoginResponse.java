package im.wity.dto;


import lombok.Builder;

@Builder
public record LoginResponse(String accessToken,String refreshToken,long accessExpirationSeconds) { }
