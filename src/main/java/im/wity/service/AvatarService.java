package im.wity.service;

import im.wity.dto.AvatarCreate;
import im.wity.entity.Avatar;
import im.wity.repository.AvatarRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final Validator validator;

    @Transactional
    public Avatar create(AvatarCreate avatarCreate){
        Set<ConstraintViolation<AvatarCreate>> violations = validator.validate(avatarCreate);
        if(!violations.isEmpty()){
            log.error(violations.toString());
            throw new IllegalArgumentException(violations.toString());
        }
        return avatarRepository.save(avatarCreate.toEntity());
    }
}
