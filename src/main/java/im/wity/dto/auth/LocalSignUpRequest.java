package im.wity.dto.auth;

import im.wity.dto.TermsAgreementDto;
import im.wity.dto.user.UserCreate;
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
