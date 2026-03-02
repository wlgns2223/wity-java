package im.wity.manager;

import im.wity.constant.BlockType;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import org.springframework.stereotype.Component;

@Component
public class BlockManager {

    public Block createBlockBy(BlockType blockType, NameCard nameCard){
        Block newBlock = blockType.create();
        nameCard.addBlock(newBlock);
        return newBlock;
    }
}
