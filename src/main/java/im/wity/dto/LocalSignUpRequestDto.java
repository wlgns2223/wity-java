package im.wity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LocalSignUpRequestDto(

        @NotNull
        @Valid
        UserCreateDto userCreateDto,

        @NotNull
        @Valid
        TermsAgreementDto terms
) {
}
