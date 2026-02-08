package im.wity.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageName {
    public static final class Validation {
        public static final String VALID_PATTERN = "a-zA-Z0-9-_";
        public static final String MESSAGE = "페이지명은 영문, 숫자, -, _만 가능합니다.";
    }

    private String pageName;

    public static PageName of(String rawPageName){
        validate(rawPageName);

        return new PageName(rawPageName);
    }

    private static void validate(String rawPageName){
        if(rawPageName == null || rawPageName.isBlank()){
            throw new IllegalArgumentException("페이지 이름을 생성해 주세요");
        }

        if(!rawPageName.matches(Validation.VALID_PATTERN)){
            throw new RuntimeException(Validation.MESSAGE);
        }
    }
}
