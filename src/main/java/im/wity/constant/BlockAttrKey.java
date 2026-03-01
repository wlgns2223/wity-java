package im.wity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockAttrKey {
    CONTENT("content"),
    URL("url"),
    SIZE("size");

    @JsonValue
    private final String key;
}
