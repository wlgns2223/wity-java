package im.wity.service;

import im.wity.dto.SignUpRequestDto;
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
    public void signUp(SignUpRequestDto signUpRequestDto){
        User user = null;
        if(signUpRequestDto.authProvider().isOauth()){

        }
        else{
            user = User.createLocalUser(signUpRequestDto.email(), signUpRequestDto.password(), signUpRequestDto.authProvider());
            userRepository.save(user);
        }
    }
}
