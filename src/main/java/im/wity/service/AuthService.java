package im.wity.service;

import im.wity.core.AuthManager;
import im.wity.core.AuthResult;
import im.wity.dto.auth.AuthCookie;
import im.wity.dto.auth.LocalSignInRequest;
import im.wity.dto.auth.LocalSignUpRequest;
import im.wity.dto.auth.SignInResponse;
import im.wity.dto.nameCard.NameCardCreate;
import im.wity.entity.User;
import im.wity.validator.SignInValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TermsOfConditionService termsOfConditionService;
    private final EmailService emailService;
    private final NameCardService nameCardService;
    private final SignInValidator signInValidator;
    private final AuthManager authManager;


    @Transactional
    public void signUp(LocalSignUpRequest localSignUpRequest)  {
        User user = userService.createLocal(localSignUpRequest.userCreate());
        termsOfConditionService.createTerm(localSignUpRequest.terms(), user);
        nameCardService.create(NameCardCreate
                .builder()
                .user(user)
                .pageName(localSignUpRequest.userCreate().defaultPageName())
                .build()
        );

        emailService.sendEmail(user.getEmail());
    }

    public void delete(Long id){
        userService.delete(id);

    }

    public SignInResponse signIn(LocalSignInRequest signInRequest){
        User user = signInValidator.validate(signInRequest);
        AuthResult authResult = authManager.process(user);
        return SignInResponse.of(authResult, user);
    }
}
