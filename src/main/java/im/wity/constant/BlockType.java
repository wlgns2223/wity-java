package im.wity.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import im.wity.dto.block.Size;
import im.wity.entity.Block;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum BlockType {

    TEXT("text",Map.of(BlockAttrKey.CONTENT,""),attrs ->
            Block.ofText((String)attrs.get(BlockAttrKey.CONTENT))),
    LINK("link",Map.of(BlockAttrKey.CONTENT,""), attrs ->
            Block.ofLink((String) attrs.get(BlockAttrKey.CONTENT))),
    IMAGE("image",
            Map.of(BlockAttrKey.URL,"",
                    BlockAttrKey.SIZE,Size.from()),
            attrs -> Block.ofImage((String) attrs.get(BlockAttrKey.URL), (Size) attrs.get(BlockAttrKey.SIZE)));

    @JsonValue
    private final String type;
    private final Map<BlockAttrKey,Object> requiredAttrs;
    private final Function<Map<BlockAttrKey,Object>, Block> factory;

    public Block create() {
        return factory.apply(requiredAttrs);
    }

    public void validate(Map<BlockAttrKey,Object> attrs){
        requiredAttrs.keySet().forEach(requiredAttr -> {
            if(!attrs.containsKey(requiredAttr)){
                throw new IllegalArgumentException(requiredAttr + "가 없습니다.");
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
