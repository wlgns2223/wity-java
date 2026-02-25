package im.wity.controller;

import im.wity.entity.Block;
import im.wity.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/namecard/block")
@RequiredArgsConstructor
public class BlockController {

    private BlockService blockService;

    @PostMapping
    ResponseEntity<Block> addBlock(){
        return null;
    }
}
