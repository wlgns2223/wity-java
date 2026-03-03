package im.wity.validator;

import im.wity.components.PasswordService;
import im.wity.dto.auth.LocalSignInRequest;
import im.wity.entity.User;
import im.wity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SignInValidator {

    private final UserService userService;
    private final PasswordService passwordService;

    @Transactional(readOnly = true)
    public User validate(LocalSignInRequest signInRequest ) {
        User user = userService.findByEmail(signInRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordService.compare(signInRequest.password(),user.getPassword())) {
            throw new RuntimeException( "이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return user;
    }
}
