package im.wity.service;

import im.wity.dto.LocalSignUpRequestDto;
import im.wity.dto.UserCreateDto;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createLocal(UserCreateDto userCreateDto){
        if(userRepository.existsUserByEmail(userCreateDto.email())){
            throw new IllegalArgumentException(userCreateDto.email() + "이미 있습니다.");

        }

        return userRepository.save(User.createLocalUser(
                userCreateDto.email(),
                userCreateDto.password(),
                userCreateDto.defaultPageName(),
                userCreateDto.userName()
        ));
    }
}
