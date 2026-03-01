package im.wity.fixture;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.entity.Block;

import java.util.Map;

public class BlockFixture {
    public static Block createTextBlock(){
        return Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of(BlockAttrKey.CONTENT,"fixture"))
                .build();
    }
}
