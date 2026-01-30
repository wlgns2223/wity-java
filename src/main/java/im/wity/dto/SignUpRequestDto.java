package im.wity.dto;

import im.wity.constant.AuthProvider;

public record SignUpRequestDto (
        String email,
        String password,
        AuthProvider authProvider){}
