package im.wity.controller;

import im.wity.constant.BlockAttrKey;
import im.wity.dto.block.BlockCreateRequest;
import im.wity.dto.block.BlockResponse;
import im.wity.entity.Block;
import im.wity.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/namecard/{nameCardId}/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping
    ResponseEntity<BlockResponse> addBlock(@PathVariable Long nameCardId, @RequestBody BlockCreateRequest blockCreateRequest){
        Block block = blockService.create(nameCardId, blockCreateRequest.blockType());
        return ResponseEntity.ok(BlockResponse.from(block));
    }

    @PostMapping("/{blockId}")
    ResponseEntity<BlockResponse> updateCustomAttrs(@PathVariable Long blockId, @RequestBody Map<BlockAttrKey,Object> dto, @PathVariable String nameCardId){
        Block block = blockService.updateCustomAttrs(blockId, dto);
        return ResponseEntity.ok(BlockResponse.from(block));
    }
}
