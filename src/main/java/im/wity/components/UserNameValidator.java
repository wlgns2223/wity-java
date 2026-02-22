package im.wity.components;

public class UserNameValidator {
    public static final String VALID_PATTERN = "^[가-힣a-zA-Z0-9]+$";
    public static final String MESSAGE = "유저이름은 한글, 영문 및 숫자만 가능합니다.";

    public static void validate(String newUserName){

        if(!newUserName.matches(VALID_PATTERN)){
            throw new RuntimeException(MESSAGE);
        }
    }
}
