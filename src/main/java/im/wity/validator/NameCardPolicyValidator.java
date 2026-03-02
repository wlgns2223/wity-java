package im.wity.validator;

import im.wity.repository.NameCardRepository;
import im.wity.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NameCardPolicyValidator {

    private final NameCardRepository nameCardRepository;


    public void validateCreate(PageName pageName){
        if(nameCardRepository.existsByPageName(pageName)){
            throw new IllegalArgumentException("다른 페이지명을 선택해주세요");
        }

    }
}
