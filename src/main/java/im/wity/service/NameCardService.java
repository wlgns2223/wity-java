package im.wity.service;

import im.wity.dto.NameCardCreate;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.repository.NameCardRepository;
import im.wity.vo.PageName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;

    @Transactional
    public NameCard create(NameCardCreate nameCardCreate){
        if(nameCardRepository.existsByPageName(nameCardCreate.pageName())){
            throw new IllegalArgumentException("다른 페이지명을 선택해주세요");
        }

        return nameCardRepository.save(
                NameCard.builder()
                        .user(nameCardCreate.user())
                        .avatar(nameCardCreate.avatar())
                        .pageName(nameCardCreate.pageName())
                        .blocks(nameCardCreate.blocks())
                        .build()
        );
    }

    public NameCard findByPageName(PageName pageName){
        return nameCardRepository.findByPageName(pageName);
    }

    @Transactional
    public void deleteById(Long id){ nameCardRepository.deleteById(id);}

    public List<NameCard> findAllByUserId(User user){
        return nameCardRepository.findAllByUser(user);
    }


}