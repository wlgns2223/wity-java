package im.wity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {

    TEXT("text"),
    LINK("link");

    private final String type;
}
