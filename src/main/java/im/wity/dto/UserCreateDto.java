package im.wity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Min(4)
        String password,

        @NotBlank
        @Pattern(
                regexp = "^[a-zA-Z0-9-_]+$",
                message = "페이지명은 영문, 숫자, -, _만 가능합니다."
        )
        String defaultPageName,

        @NotBlank
        @Pattern(
                regexp = "^[가-힣a-zA-Z0-9]+$",
                message = "유저이름은 한글, 영문 및 숫자만 가능합니다."
        )
        String userName
) {
}
