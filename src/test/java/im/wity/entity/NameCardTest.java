package im.wity.entity;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("NameCard Entity Test")
public class NameCardTest {

    @Test
    @DisplayName("addBlock - 빈 블록 목록에 블록을 추가하면 order 0이 할당되고 nameCard가 설정된다")
    void addBlock_toEmptyBlocks_assignsOrderZeroAndSetsNameCard() {
        // given
        NameCard nameCard = NameCard.builder()
                .blocks(new LinkedHashSet<>())
                .build();

        Block block = Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of())
                .build();

        // when
        nameCard.addBlock(block);

        // then
        assertThat(nameCard.getBlocks()).hasSize(1).contains(block);
        assertThat(block.getBlockOrder()).isEqualTo(0);
        assertThat(block.getNameCard()).isEqualTo(nameCard);
    }

    @Test
    @DisplayName("addBlock - 여러 블록들을 추가핬을때 순서가 추가한 순서대로 초기화 된다.")
    void addBlock_assignBlockOrders(){
        NameCard nameCard = NameCard.builder().blocks(new LinkedHashSet<>()).build();
        Block firstBlock = Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of(BlockAttrKey.KEY,"first"))
                .build();
        Block secondBlock = Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of(BlockAttrKey.KEY,"second"))
                .build();
        nameCard.addBlock(firstBlock);
        nameCard.addBlock(secondBlock);

        System.out.println(nameCard.getBlocks());

        assertThat(nameCard.getBlocks()).hasSize(2).contains(firstBlock, secondBlock);
        AtomicInteger i= new AtomicInteger(0);
        nameCard.getBlocks().forEach((b) -> {
            assertThat(b.getBlockOrder()).isEqualTo(i.get());
            assertThat(b.getNameCard()).isEqualTo(nameCard);
            i.getAndIncrement();
        });

    }
}
