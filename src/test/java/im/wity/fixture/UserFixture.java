package im.wity.fixture;

import im.wity.entity.User;
import im.wity.vo.PageName;
import im.wity.vo.Password;

import static org.mockito.Mockito.*;

public class UserFixture {

    public static User createLocalUser(){
        String email = "test@example.com";
        Password encodedPassword = mock(Password.class);
        String username = "foo";

        return User.createLocalUser(
                email,
                encodedPassword,
                PageName.of("testPage"),
                username
        );
    }
}
