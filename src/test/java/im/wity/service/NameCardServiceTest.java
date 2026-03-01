package im.wity.service;

import im.wity.dto.nameCard.NameCardCreate;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.fixture.UserFixture;
import im.wity.repository.NameCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("NameCard Service Test")
@ExtendWith(MockitoExtension.class)
public class NameCardServiceTest {

    @Mock
    private NameCardRepository nameCardRepository;

    @InjectMocks
    NameCardService nameCardService;

    @Test
    @DisplayName("페이지명이 중복되지 않으면 NameCard를 생성한다.")
    void createNameCard(){
        User user = UserFixture.createLocalUser();
        LinkedHashSet<Block> blocks = new LinkedHashSet<>();

        NameCardCreate nameCardCreate = NameCardCreate.builder()
                .user(user)
                .pageName(user.getDefaultPageName())
                .build();

        NameCard expectedNameCard = NameCard.builder()
                .user(user)
                .pageName(user.getDefaultPageName())
                .blocks(blocks)
                .build();

        given(nameCardRepository.save(any(NameCard.class))).willReturn(expectedNameCard);

        NameCard result = nameCardService.create(nameCardCreate);

        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getPageName()).isEqualTo(user.getDefaultPageName());
        assertThat(result.getBlocks()).isEqualTo(blocks);

        verify(nameCardRepository).existsByPageName(user.getDefaultPageName());

    }

    @Test
    @DisplayName("페이지명이 중복되면 NameCard 생성에 실패한다")
    void createNameCard_ThrowsException_WhenPageNameExists(){
        User user = UserFixture.createLocalUser();

        NameCardCreate nameCardCreate = NameCardCreate.builder()
                .user(user)
                .pageName(user.getDefaultPageName())
                .build();

        given(nameCardRepository.existsByPageName(user.getDefaultPageName()))
                .willReturn(true);

        assertThatThrownBy(() -> nameCardService.create(nameCardCreate))
                .isInstanceOf(IllegalArgumentException.class);

        verify(nameCardRepository).existsByPageName(user.getDefaultPageName());

    }
}
