package gitME.auth.service;

import gitME.auth.dto.SignUpDataDTO;
import gitME.entity.dto.GitHubDataDTO;
import gitME.repository.UserRepository;
import gitME.user.service.CardDataService;
import gitME.user.service.GitHubDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {

    private final GitHubDataService gitHubDataService;
    private final CardDataService cardDataService;
    private final UserRepository userRepository;

    public void createUser(SignUpDataDTO signUpDataDTO) throws Exception {
        try {
            System.out.println("signUpDataDTO = " + signUpDataDTO);
            GitHubDataDTO gitAllInfoDTO = gitHubDataService.getData(signUpDataDTO.getGitAccessToken());
            System.out.println("gitAllInfo = " + gitAllInfoDTO);

            //받아온 데이터를 jpa를 사용해서 저장한다.
            cardDataService.saveData(signUpDataDTO, gitAllInfoDTO);

        } catch (Exception e) {
            log.error("createUser: error", e);
            throw e;

        }
    }

    public int getUserIdxByKakaoId(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new NoSuchElementException("No user found with kakaoId: " + kakaoId))
                .getIdx();
    }
}
