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

    @Transactional
    public NameCard create(Long nameCardId, BlockType blockType){
        NameCard nameCard = nameCardService.getById(nameCardId);
        nameCard.addBlock(Block.from(blockType));

        return nameCard;
    }


}
