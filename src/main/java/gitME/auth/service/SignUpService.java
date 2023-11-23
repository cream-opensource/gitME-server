package gitME.auth.service;

import gitME.auth.dto.SignUpDataDTO;
import gitME.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserService userService;

    public void createUser(SignUpDataDTO signUpDataDTO){

        try {
            System.out.println("signUpDataDTO = " + signUpDataDTO);

            Map<String, Object> gitAllInfo = userService.getGitAllInfo(signUpDataDTO.getGitAccessToken());
            System.out.println("gitAllInfo = " + gitAllInfo);
        } catch (Exception e) {
            log.error("createUser: error", e);
            throw e;
        }
    }
}
