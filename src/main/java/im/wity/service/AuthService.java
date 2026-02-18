package im.wity.service;

import im.wity.components.JwtProvider;
import im.wity.components.PasswordFactory;
import im.wity.dto.LocalSignInRequest;
import im.wity.dto.LocalSignUpRequest;
import im.wity.dto.LoginResponse;
import im.wity.dto.NameCardCreate;
import im.wity.entity.Avatar;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TermsOfConditionService termsOfConditionService;
    private final EmailService emailService;
    private final NameCardService nameCardService;
    private final BlockService blockService;
    private final PasswordFactory passwordFactory;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(LocalSignUpRequest localSignUpRequest)  {
        User user = userService.createLocal(localSignUpRequest.userCreate());
        termsOfConditionService.createTerm(localSignUpRequest.terms(), user);
        NameCard nameCard = nameCardService.create(NameCardCreate
                .builder()
                .user(user)
                .pageName(localSignUpRequest.userCreate().defaultPageName())
                .avatar(Avatar.init())
                .build()
        );
        Set<Block> blocks = blockService.initOnNameCardCreate();
        blocks.forEach(nameCard::addBlock);
        emailService.sendEmail(user.getEmail());
    }

    public void delete(Long id){
        userService.delete(id);

    }

    @Transactional
    public LoginResponse signIn(LocalSignInRequest signInRequest){
        User user = userService.findByEmail(signInRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordFactory.compare(signInRequest.password(), user.getPassword())) {
            throw new AuthenticationServiceException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(user.getEmail());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpirationSeconds(jwtProvider.getAccessExpirationSeconds())
                .build();
    }
}
