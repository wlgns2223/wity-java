package im.wity.controller;

import im.wity.dto.BlockCreateRequest;
import im.wity.dto.NameCardResponse;
import im.wity.entity.NameCard;
import im.wity.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/namecard/{nameCardId}/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping
    ResponseEntity<NameCardResponse> addBlock(@PathVariable Long nameCardId, @RequestBody BlockCreateRequest blockCreateRequest){
        NameCard nameCard = blockService.create(nameCardId, blockCreateRequest.blockType());
        return ResponseEntity.ok(NameCardResponse.from(nameCard));
    }
}
