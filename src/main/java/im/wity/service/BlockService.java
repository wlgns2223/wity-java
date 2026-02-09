package im.wity.service;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;

    public Set<Block> initOnNameCardCreate(){
        Set<Block> blocks = new LinkedHashSet<>();
        blocks.add(Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of(BlockAttrKey.KEY, "위티 생성을 축하드립니다."))
                .build()
        );
        blocks.add(Block.builder()
                .type(BlockType.LINK)
                .customAttrs(Map.of(BlockAttrKey.KEY, "https://wity.im"))
                .build()
        );


        return blocks;


    }
}
