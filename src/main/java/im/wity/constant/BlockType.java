package im.wity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum BlockType {

    TEXT("text",Set.of(BlockAttrKey.KEY)),
    LINK("link",Set.of(BlockAttrKey.KEY));

    @JsonValue
    private final String type;
    private final Set<BlockAttrKey> requiredAttrs;

    public static BlockType from(String value){
        return Arrays.stream(values())
                .filter(bt -> bt.type.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 블록타입니다."));
    }
}
