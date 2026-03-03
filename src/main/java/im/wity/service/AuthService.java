package im.wity.service;

import im.wity.components.AuthGenerator;
import im.wity.components.JwtProvider;
import im.wity.components.PasswordService;
import im.wity.dto.auth.AuthToken;
import im.wity.dto.auth.LocalSignInRequest;
import im.wity.dto.auth.LocalSignUpRequest;
import im.wity.dto.auth.LocalSignInResponse;
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
    private final AuthGenerator authGenerator;

    private final PasswordService passwordService;
    private final JwtProvider jwtProvider;

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

    public LocalSignInResponse signIn(LocalSignInRequest signInRequest){
        User user = signInValidator.validate(signInRequest);
        AuthToken authToken = authGenerator.generate(user.getEmail());
        return LocalSignInResponse.from(authToken, user);
    }
}
