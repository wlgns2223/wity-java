package im.wity.dto;

import im.wity.constant.TermsOfCondType;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record TermsAgreementDto(
        @NotBlank(message = "약관 동의는 필수입니다.")
        Map<TermsOfCondType,Boolean> agreements)
{
    public TermsAgreementDto {
        if(agreements == null || agreements.isEmpty()){
            throw new IllegalArgumentException("약관 동의는 필수입니다.");
        }
        validateRequiredTerms();
    }

    public void validateRequiredTerms(){
        List<TermsOfCondType> terms = Arrays.stream(TermsOfCondType.values())
                .filter(TermsOfCondType::isRequired)
                .filter(type -> !Boolean.TRUE.equals(agreements.get(type)))
                .toList();

        if(!terms.isEmpty() ) {
            throw new IllegalArgumentException(terms + "약관은 필수입니다.");
        }
    }
}