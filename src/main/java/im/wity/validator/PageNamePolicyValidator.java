package im.wity.validator;

import im.wity.entity.User;
import im.wity.service.NameCardService;
import im.wity.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageNamePolicyValidator {
    private final NameCardService nameCardService;

    public void validateIfExists(User user, PageName pageName){
        if(!nameCardService.existsByUserAndPageName(user, pageName)){
            throw new RuntimeException(pageName + "이 없습니다.");
        }
    }
}
