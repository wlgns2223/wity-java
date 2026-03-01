package im.wity.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LocalSignInRequest(
        @NotNull
        @Email
        String email,

        @NotNull
        String password
) {
}
