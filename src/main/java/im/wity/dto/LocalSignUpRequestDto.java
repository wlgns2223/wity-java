package im.wity.dto;

import im.wity.constant.AuthProvider;

public record LocalSignUpRequestDto(
        String email,
        String password,
        String defaultPageName,
        String userName
        ){}
