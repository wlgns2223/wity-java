package im.wity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LocalSignUpRequest(

        @NotNull
        @Valid
        UserCreate userCreate,

        @NotNull
        @Valid
        TermsAgreementDto terms
) {
}
