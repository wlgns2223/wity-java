package im.wity.service;

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

    public Set<Block> initOnNameCardCreate(NameCard nameCard){
        Set<Block> blocks = new LinkedHashSet<>();
        blocks.add(Block.builder()
                .type("TEXT")
                .customAttrs(Map.of("content", "위티 생성을 축하드립니다."))
                .nameCard(nameCard)
                .build()
        );


        return blocks;


    }
}
