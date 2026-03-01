package im.wity.dto.block;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.entity.Block;
import java.util.Map;

public record BlockResponse(
        Long id,
        Boolean folded,
        Boolean active,
        BlockType type,
        Integer clickCount,
        Integer blockOrder,
        Map<BlockAttrKey,Object> customAttrs

) {

    public static BlockResponse from(Block block){
        return new BlockResponse(
                block.getId(),
                block.getFolded(),
                block.getActive(),
                block.getType(),
                block.getClickCount(),
                block.getBlockOrder(),
                block.getCustomAttrs()
        );
    }
}
