package im.wity.service;

import im.wity.dto.LocalSignUpRequestDto;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createLocal(LocalSignUpRequestDto localSignUpRequestDto){
        if(userRepository.existsUserByEmail(localSignUpRequestDto.email())){
            throw new IllegalArgumentException(localSignUpRequestDto.email() + "이미 있습니다.");

        }

        return userRepository.save(User.createLocalUser(
                localSignUpRequestDto.email(),
                localSignUpRequestDto.password(),
                localSignUpRequestDto.defaultPageName(),
                localSignUpRequestDto.userName()
        ));
    }
}
