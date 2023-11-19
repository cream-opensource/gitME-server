package gitME.uncategorized.controller;

import gitME.uncategorized.dto.SignUpDataDTO;
import gitME.uncategorized.service.InterlinkService;
import gitME.uncategorized.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private InterlinkService interlinkService;

    @PostMapping("/signUp")
    public ResponseEntity<String> submitSignUpData(@RequestBody SignUpDataDTO signUpDataDTO) {
        try {
            signUpService.createUser(signUpDataDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("userSignUpData post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during userSignUpData post");
        }
    }

    @GetMapping("/gitinfo/{assesstoken}")
    public Map<String, String> getGitInfo(@PathVariable("assesstoken") String token) {
        Map<String, String> gitInfo = interlinkService.getGitInfo(token);
        interlinkService.getCommits(token, gitInfo.get("nickname"));
        return gitInfo;
    }
}
