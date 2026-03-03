package im.wity.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CookieName {
    ACCESS("access"),
    REFRESH("refresh");

    private final String value;

}
