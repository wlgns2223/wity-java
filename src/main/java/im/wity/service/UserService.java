package im.wity.service;

import im.wity.components.PasswordFactory;
import im.wity.dto.LocalSignUpRequestDto;
import im.wity.dto.UserCreateDto;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordFactory passwordFactory;

    @Transactional
    public User createLocal(UserCreateDto userCreateDto){
        if(userRepository.existsUserByEmail(userCreateDto.email())){
            throw new IllegalArgumentException(userCreateDto.email() + "이미 있습니다.");

        }

        try{
            return userRepository.save(User.createLocalUser(
                    userCreateDto.email(),
                    userCreateDto.password(),
                    userCreateDto.defaultPageName(),
                    userCreateDto.userName(),
                    passwordFactory
            ));

        } catch (DataIntegrityViolationException exception){
            throw new IllegalArgumentException(userCreateDto.email() + "는 이미 가입된 이메일입니다.");
        }

    }
}
