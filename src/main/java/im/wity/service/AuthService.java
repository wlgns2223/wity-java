package im.wity.service;

import im.wity.dto.LocalSignUpRequest;
import im.wity.dto.NameCardCreate;
import im.wity.entity.Avatar;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    public User signUp(LocalSignUpRequest localSignUpRequest)  {
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

        return user;
    }
}
