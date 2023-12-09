package gitME.auth.controller;

import gitME.auth.service.SignService;
import gitME.auth.dto.SignUpDataDTO;
import gitME.user.service.GitHubDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;
    private final GitHubDataService gitHubDataService;

    @GetMapping("/sign/{kakaoId}")
    public ResponseEntity<?> checkSign(@PathVariable("kakaoId") String kakaoId) {
        try {
            int userIdx = signService.getUserIdxByKakaoId(kakaoId);
            Map<String, Integer> userMap = new HashMap<>();
            userMap.put("userIdx", userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(userMap);
        } catch (NoSuchElementException e) {
            // NoSuchElementException을 별도로 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // 다른 종류의 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during checkSign get");
        }
    }

    @GetMapping("/github/language/{accessToken}")
    public ResponseEntity<?> getGithubAggregateLanguages(@PathVariable("accessToken") String accessToken) {
        try {
            Map<String, Integer> aggregatedLanguages = gitHubDataService.getAggregatedLanguages(accessToken);
            return ResponseEntity.status(HttpStatus.OK).body(aggregatedLanguages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during GithubAggregateLanguages get");

        }

    }

    @PostMapping("/signUp")
    public ResponseEntity<String> submitSignUpData(@RequestBody SignUpDataDTO signUpDataDTO) {
        try {
            signService.createUser(signUpDataDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("userSignUpData post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during userSignUpData post");
        }
    }


}