package im.wity.service;

import im.wity.constant.BlockType;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.fixture.NameCardFixture;
import im.wity.fixture.UserFixture;
import im.wity.vo.PageName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Block Service Test Suites")
@ExtendWith(MockitoExtension.class)
public class BlockServiceTest {

    @Mock
    private NameCardService nameCardService;

    @InjectMocks
    private BlockService blockService;

    @Test
    @DisplayName("nameCard Id와 BlockType이 있으면 Block을 생성한다.")
    void createBlock(){
        NameCard nameCard = NameCardFixture.create(UserFixture.createLocalUser());
        given(nameCardService.getById(any(Long.class))).willReturn(nameCard);

        NameCard result = blockService.create(any(Long.class), BlockType.TEXT);

        assertThat(result).isNotNull();
        assertThat(result.getBlocks()).contains(Block.from(BlockType.TEXT));


    }
}
