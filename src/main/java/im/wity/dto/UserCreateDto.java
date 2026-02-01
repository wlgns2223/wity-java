package im.wity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String defaultPageName,

        @NotBlank
        String userName
) {
}
