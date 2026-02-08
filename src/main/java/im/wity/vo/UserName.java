package im.wity.vo;

public record UserName(String username) {
    public static final class Validation {
        public static final String VALID_PATTERN = "^[가-힣a-zA-Z0-9]+$";
        public static final String MESSAGE = "유저이름은 한글, 영문 및 숫자만 가능합니다.";
    }

    public UserName {
        validate(username);
    }

    private static void validate(String rawUserName ){
        if(rawUserName == null || rawUserName.isEmpty()){
            throw new IllegalArgumentException("유저이름을 생성해 주세요.");
        }

        if(!rawUserName.matches(Validation.VALID_PATTERN)){
            throw new IllegalArgumentException(Validation.MESSAGE);
        }
    }
}
