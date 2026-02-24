package im.wity.service;

import im.wity.dto.NameCardCreate;
import im.wity.dto.NameCardCreateRequest;
import im.wity.entity.Avatar;
import im.wity.entity.Block;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.repository.NameCardRepository;
import im.wity.vo.PageName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;
    private final BlockService blockService;

    @Transactional
    public NameCard create(NameCardCreate nameCardCreate){
        if(nameCardRepository.existsByPageName(nameCardCreate.pageName())){
            throw new IllegalArgumentException("다른 페이지명을 선택해주세요");
        }

        Set<Block> blocks = blockService.initOnNameCardCreate();
        NameCard nameCard = NameCard.builder()
                .user(nameCardCreate.user())
                .avatar(Avatar.init())
                .pageName(nameCardCreate.pageName())
                .build();

        blocks.forEach(nameCard::addBlock);

        return nameCardRepository.save(nameCard);
    }

    public NameCard findByPageName(PageName pageName){
        return nameCardRepository.findByPageName(pageName);
    }

    @Transactional
    public void deleteById(Long id){ nameCardRepository.deleteById(id);}

    public boolean existsByUserAndPageName(User user, PageName pageName){
        return nameCardRepository.existsByUserAndPageName(user, pageName);
    }


}