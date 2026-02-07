package im.wity.service;

import im.wity.entity.NameCard;
import im.wity.repository.NameCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;

    public NameCard create(){

        return null;
    }


}