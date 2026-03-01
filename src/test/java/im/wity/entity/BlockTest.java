package im.wity.entity;

import im.wity.fixture.BlockFixture;
import im.wity.fixture.NameCardFixture;
import im.wity.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Block Entity Test")
public class BlockTest {

    @Test
    @DisplayName("setNameCard - nameCard를 초기화하면 nameCard에도 해당 block이 입력된다")
    void setNameCard_nameCardInitAndNameCardBlocksContainThis(){

        NameCard nameCard = NameCardFixture.create(UserFixture.createLocalUser());
        Block block = BlockFixture.createTextBlock();
        block.setNameCard(nameCard);

        assertThat(block.getNameCard()).isEqualTo(nameCard);
        assertThat(nameCard.getBlocks()).contains(block);
    }
}
