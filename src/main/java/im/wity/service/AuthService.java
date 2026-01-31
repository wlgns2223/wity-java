package im.wity.service;

import im.wity.dto.LocalSignUpRequestDto;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(LocalSignUpRequestDto localSignUpRequestDto){
        User user = null;
    }
}
