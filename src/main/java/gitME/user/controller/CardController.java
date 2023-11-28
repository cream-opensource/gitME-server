package gitME.user.controller;

import gitME.user.dto.totalInfoDTO;
import gitME.user.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cardInfo/{idx}")
    public void getCardInfo(@PathVariable("idx") int idx) {
        System.out.println("hihi");
        cardService.getInfo(idx);
        System.out.println("bye");
    }
}
