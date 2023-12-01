package gitME.user.controller;

import gitME.entity.User;
import gitME.entity.dto.GitHubDataDTO;
import gitME.repository.GithubUserRepository;
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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;
    private final GitHubDataService gitHubDataService;
    private final GithubUserRepository githubUserRepository;
    private final UserDataService userDataService;

    @GetMapping("/cardInfo/{userIdx}")
    public ResponseEntity<?> getCardInfo(@PathVariable("userIdx") int userIdx) {
        try {
            TotalInfoDTO totalInfoDTO = cardService.getInfo(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(totalInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving card information");
        }
    }

    @PostMapping("/cardVisibility")
    public ResponseEntity<String> submitCardVisibilityConfig(@RequestBody CardVisibilityConfigDTO cardVisibilityConfigDTO) throws Exception {
        try {
            cardService.saveCardVisibilityConfig(cardVisibilityConfigDTO);
            return ResponseEntity.status(HttpStatus.OK).body("cardVisibilityConfig post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during cardVisibilityConfigData post");
        }
    }

    @PutMapping("/githubData")
    public ResponseEntity<String> updateGithubData(@RequestBody Map<String, Integer> userIdxMap) {
        try {
//            List<ExternalLink> externalLinks = externalLinkRepository.findByUserIdx(userIdx);
//            User user = userRepository.findByIdx(Idx);
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
}
