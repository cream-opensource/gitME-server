package gitME.user.controller;

import gitME.entity.Card;
import gitME.user.dto.CardDTO;
import gitME.user.dto.CardVisibilityConfigDTO;
import gitME.user.dto.TotalInfoDTO;
import gitME.user.dto.UserDataDTO;
import gitME.user.service.CardService;
import gitME.user.service.GitHubDataService;
import gitME.user.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;
    private final GitHubDataService gitHubDataService;
    private final UserDataService userDataService;

    @GetMapping("/cardInfo/{userIdx}")
    public ResponseEntity<?> getCardInfo(@PathVariable("userIdx") int userIdx) {
        try {
            TotalInfoDTO totalInfoDTO = cardService.getInfo(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(totalInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during card information get");
        }
    }

    @PostMapping("/cardVisibility")
    public ResponseEntity<String> submitCardVisibilityConfig(@RequestBody CardVisibilityConfigDTO cardVisibilityConfigDTO) {
        try {
            cardService.saveCardVisibilityConfig(cardVisibilityConfigDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("cardVisibilityConfig post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during cardVisibilityConfigData post");
        }
    }

    @PutMapping("/githubData")
    public ResponseEntity<String> updateGithubData(@RequestBody Map<String, Integer> userIdxMap) {
        try {
            gitHubDataService.updateData(userIdxMap.get("userIdx"));

            return ResponseEntity.status(HttpStatus.OK).body("GithubData put successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during GithubData put");

        }
    }

    @PutMapping("/userData")
    public ResponseEntity<String> updateUserData(@RequestBody UserDataDTO userDataDTO) {
        try {
            userDataService.updateUserData(userDataDTO);
            return ResponseEntity.status(HttpStatus.OK).body("updateUserData put successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during updateUserData put");
        }
    }

    @PostMapping("/card")
    public ResponseEntity<String> submitCard(@RequestBody CardDTO cardDTO) {
        try {
            cardService.createCard(cardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Card post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during Card post");
        }
    }

    @GetMapping("/card/{userIdx}")
    public ResponseEntity<?> getCard(@PathVariable("userIdx") int userIdx) {
        try {
            List<Card> cards = cardService.getCard(userIdx);
            return ResponseEntity.status(HttpStatus.CREATED).body(cards);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during Card get");
        }
    }
}
