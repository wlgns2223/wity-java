package im.wity.service;

import im.wity.dto.LocalSignUpRequestDto;
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

    @Transactional
    public User signUp(LocalSignUpRequestDto localSignUpRequestDto)  {
        User user = userService.createLocal(localSignUpRequestDto.userCreateDto());
        termsOfConditionService.createTerm(localSignUpRequestDto.terms(), user);
        emailService.sendEmail(user.getEmail());

        return user;
    }
}
