package im.wity.validator;

import im.wity.components.UserNameValidator;
import im.wity.dto.user.UserUpdate;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import im.wity.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPolicyValidator {

    private final UserRepository userRepository;
    private final PageNamePolicyValidator pageNamePolicyValidator;

    public void validateCreate(String email){
        if(userRepository.existsUserByEmail(email)){
            throw new IllegalArgumentException(email + "는 이미 가입된 이메일입니다.");
        }
    }

    public UserUpdate validateUpdate(User user, PageName pageName, String  userName){

        pageNamePolicyValidator.validateIfExists(user,pageName);
        UserNameValidator.validate(userName);

        return UserUpdate.builder().pageName(pageName).userName(userName).build();

    }


}
