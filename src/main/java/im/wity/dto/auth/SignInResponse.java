package im.wity.dto.auth;

import im.wity.core.AuthResult;
import im.wity.dto.user.UserResponse;
import im.wity.entity.User;

public record SignInResponse(AuthResult authResult, UserResponse userResponse) {
    public static SignInResponse of(AuthResult authResult, User user){
        return new SignInResponse(authResult, UserResponse.from(user));
    }
}
