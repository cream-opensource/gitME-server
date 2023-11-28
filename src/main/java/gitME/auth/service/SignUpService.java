package gitME.auth.service;

import gitME.auth.dto.SignUpDataDTO;
import gitME.entity.dto.GitHubDataDTO;
import gitME.user.DataJpaService;
import gitME.user.GitHubDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final GitHubDataService gitHubDataService;
    private final DataJpaService dataJpaService;

    public void createUser(SignUpDataDTO signUpDataDTO) throws Exception {
        try {
            System.out.println("signUpDataDTO = " + signUpDataDTO);
            GitHubDataDTO gitAllInfoDTO = gitHubDataService.getData(signUpDataDTO.getGitAccessToken());
            System.out.println("gitAllInfo = " + gitAllInfoDTO);

            //받아온 데이터를 jpa를 사용해서 저장한다.
            dataJpaService.saveData(signUpDataDTO, gitAllInfoDTO);

        } catch (Exception e) {
            log.error("createUser: error", e);
            throw e;

        }
    }
}
