package im.wity.service;

import im.wity.components.PasswordFactory;
import im.wity.dto.UserCreate;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import im.wity.vo.PageName;
import im.wity.vo.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("User Service Test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordFactory passwordFactory;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("이메일이 중복되지 않으면 로컬 유저를 생성한다")
    void createLocal_Success() {
        // given
        String email = "test@example.com";
        String password = "password123";
        PageName pageName = PageName.of("testpage");
        String userName = "테스트유저";

        UserCreate userCreate = new UserCreate(email, password, pageName, userName);
        Password encodedPassword = mock(Password.class);
        User expectedUser = User.createLocalUser(email, encodedPassword, pageName, userName);

        given(userRepository.existsUserByEmail(email)).willReturn(false);
        given(passwordFactory.create(password)).willReturn(encodedPassword);
        given(userRepository.save(any(User.class))).willReturn(expectedUser);

        // when
        User result = userService.createLocal(userCreate);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getUserName()).isEqualTo(userName);
        assertThat(result.getDefaultPageName()).isEqualTo(pageName);

        verify(userRepository).existsUserByEmail(email);
        verify(passwordFactory).create(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("이메일이 이미 존재하면 예외를 발생시킨다")
    void createLocal_ThrowsException_WhenEmailExists() {
        // given
        String email = "duplicate@example.com";
        String password = "password123";
        PageName pageName = PageName.of("testpage");
        String userName = "테스트유저";

        UserCreate userCreate = new UserCreate(email, password, pageName, userName);

        given(userRepository.existsUserByEmail(email)).willReturn(true);

        // when & then
        assertThatThrownBy(() -> userService.createLocal(userCreate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(email + "는 이미 가입된 이메일입니다.");

        verify(userRepository).existsUserByEmail(email);
        verify(passwordFactory, never()).create(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}
