package im.wity.service;

import im.wity.dto.LocalSignUpRequest;
import im.wity.dto.NameCardCreate;
import im.wity.entity.Avatar;
import im.wity.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TermsOfConditionService termsOfConditionService;
    private final EmailService emailService;
    private final NameCardService nameCardService;
    private final AvatarService avatarService;

    @Transactional
    public User signUp(LocalSignUpRequest localSignUpRequest)  {
        User user = userService.createLocal(localSignUpRequest.userCreate());
        termsOfConditionService.createTerm(localSignUpRequest.terms(), user);
        nameCardService.create(NameCardCreate
                .builder()
                .user(user)
                .pageName(localSignUpRequest.userCreate().defaultPageName())
                .avatar(Avatar.init())
                .build()
        );
        emailService.sendEmail(user.getEmail());

        return user;
    }
}
