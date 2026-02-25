package im.wity.service;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
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

import java.util.*;

@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;

    @Transactional
    public NameCard create(NameCardCreate nameCardCreate){
        if(nameCardRepository.existsByPageName(nameCardCreate.pageName())){
            throw new IllegalArgumentException("다른 페이지명을 선택해주세요");
        }

        NameCard nameCard = NameCard.create(nameCardCreate.user(), nameCardCreate.pageName());

        return nameCardRepository.save(nameCard);
    }

    public NameCard findByPageName(PageName pageName){
        return nameCardRepository.findByPageName(pageName);
    }

    public NameCard getById(Long id){
        return nameCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "로 조회된 결과가 없습니다."));
    }

    @Transactional
    public void deleteById(Long id){ nameCardRepository.deleteById(id);}

    public boolean existsByUserAndPageName(User user, PageName pageName){
        return nameCardRepository.existsByUserAndPageName(user, pageName);
    }


}