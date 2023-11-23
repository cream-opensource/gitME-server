package gitME.auth.controller;

import gitME.auth.service.SignUpService;
import gitME.auth.dto.SignUpDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<String> submitSignUpData(@RequestBody SignUpDataDTO signUpDataDTO) {
        try {
            signUpService.createUser(signUpDataDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("userSignUpData post successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during userSignUpData post");
        }
    }
}
