package im.wity.service;

import im.wity.dto.TermsAgreementDto;
import im.wity.entity.TermsOfCondition;
import im.wity.entity.User;
import im.wity.repository.TermsOfConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsOfConditionService {

    private final TermsOfConditionRepository termsRepository;

    public List<TermsOfCondition> createTerm(TermsAgreementDto termsDto, User user){

        List<TermsOfCondition> terms = termsDto
                .agreements()
                .entrySet()
                .stream()
                .map(e -> TermsOfCondition.builder().user(user).type(e.getKey()).agreed(e.getValue()).build())
                .toList();

        return termsRepository.saveAll(terms);

    }

}
