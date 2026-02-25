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

    public void validate(Map<BlockAttrKey,Object> attrs){
        requiredAttrs.forEach(key -> {
            if(!attrs.containsKey(key)){
                throw new IllegalArgumentException(
                        this.type + " 블록에는 " + key + " 속성이 필요합니다.");
            }
        });
    }

    public static BlockType from(String value){
        return Arrays.stream(values())
                .filter(bt -> bt.type.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 블록타입니다."));
    }
}
