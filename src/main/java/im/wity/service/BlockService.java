package im.wity.service;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.manager.BlockManager;
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
    private final BlockManager blockManager;

    @Transactional
    public Block create(Long nameCardId, BlockType blockType){
        NameCard nameCard = nameCardService.getById(nameCardId);
        return blockManager.createBlockBy(blockType, nameCard);
    }

    @Transactional
    public Block updateCustomAttrs(Long nameCardId, Long blockId, Map<BlockAttrKey,Object> newCustomAttrs, User user){
        Block block = blockRepository.findByIdAndNameCard_IdAndNameCard_User(blockId,nameCardId,user).orElseThrow(() ->
                new RuntimeException(blockId + "가 없습니다."));

        block.updateCustomAttrs(newCustomAttrs);
        return block;
    }

    @Transactional
    public void increaseCount(Long nameCardId, Long blockId){
        Block block = blockRepository.findByIdAndNameCard_Id(blockId, nameCardId)
                .orElseThrow(() -> new RuntimeException(blockId + "가 없습니다."));

        block.increaseClickCount();
    }


}
