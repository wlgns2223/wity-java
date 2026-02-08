package im.wity.service;

import im.wity.entity.Block;
import im.wity.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;

    public List<Block> init(){

    }
}
