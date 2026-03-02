package im.wity.service;

import im.wity.dto.nameCard.NameCardCreate;
import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.repository.NameCardRepository;
import im.wity.validator.NameCardPolicyValidator;
import im.wity.vo.PageName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;
    private final NameCardPolicyValidator nameCardPolicyValidator;

    @Transactional
    public NameCard create(NameCardCreate nameCardCreate){
        nameCardPolicyValidator.validateCreate(nameCardCreate.pageName());
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