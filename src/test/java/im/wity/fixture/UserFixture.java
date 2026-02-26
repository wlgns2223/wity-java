package im.wity.fixture;

import im.wity.entity.User;
import im.wity.vo.PageName;
import im.wity.vo.Password;

import static org.mockito.Mockito.*;

public class UserFixture {

    private static String email = "foo@example.com";
    private static String userName = "foo";

    public static User createLocalUser(){
        Password encodedPassword = mock(Password.class);

        return User.createLocalUser(
                email,
                encodedPassword,
                PageName.of("testPage"),
                userName
        );
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserFixture.email = email;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserFixture.userName = userName;
    }
}
