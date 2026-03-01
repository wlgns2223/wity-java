package im.wity.service;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.repository.BlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final NameCardService nameCardService;
    private final BlockRepository blockRepository;

    @Transactional
    public Block create(Long nameCardId, BlockType blockType){
        NameCard nameCard = nameCardService.getById(nameCardId);
        Block newBlock = blockType.create();
        nameCard.addBlock(newBlock);

        return newBlock;
    }

    @Transactional
    public Block updateCustomAttrs(Long blockId, Map<BlockAttrKey,Object> newCustomAttrs){
        Block block = blockRepository.findById(blockId).orElseThrow(() ->
                new RuntimeException(blockId + "가 없습니다."));

        block.updateCustomAttrs(newCustomAttrs);
        return block;
    }


}
