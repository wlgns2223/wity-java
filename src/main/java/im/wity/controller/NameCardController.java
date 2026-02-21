package im.wity.controller;

import im.wity.dto.NameCardResponse;
import im.wity.entity.NameCard;
import im.wity.service.NameCardService;
import im.wity.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/namecard")
@RequiredArgsConstructor
public class NameCardController {
    private final NameCardService nameCardService;

    @GetMapping("/{pageName}")
    public ResponseEntity<NameCardResponse> findByPageName(@PathVariable String pageName){
        NameCard nameCard = nameCardService.findByPageName(PageName.of(pageName));
        return ResponseEntity.ok(NameCardResponse.from(nameCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        nameCardService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
