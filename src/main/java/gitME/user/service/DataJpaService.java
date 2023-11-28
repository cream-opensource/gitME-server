package gitME.user.service;

import gitME.auth.dto.SignUpDataDTO;
import gitME.entity.CodeStack;
import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.entity.vo.GitHubData;
import gitME.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataJpaService {
    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final RepositoryRepository repositoryRepository;
    private final CodeStackRepository codeStackRepository;
    private final ExternalLinkRepository externalLinkRepository;

    @Transactional
    public void saveData(SignUpDataDTO signUpDataDTO, GitHubData gitHubData) {
        try {
            // User 엔티티 생성 및 저장
            User user = new User();
            user.setKakaoId(signUpDataDTO.getKakaoId());
            user.setName(signUpDataDTO.getName());
            user.setBirthDate(signUpDataDTO.getBirthDate());
            user.setEmail(signUpDataDTO.getEmail());
            user.setPhone(signUpDataDTO.getPhone());
            userRepository.save(user);

            // GitHubUser 엔티티 생성 및 저장
            GithubUser githubUser = new GithubUser();
            githubUser.setUserIdx(user.getIdx());
            githubUser.setAccessToken(signUpDataDTO.getGitAccessToken());
            githubUser.setNickname(gitHubData.getNickname());
            githubUser.setAvatarUrl(gitHubData.getAvatarUrl());
            githubUser.setFollowers(gitHubData.getFollowers());
            githubUser.setFollowing(gitHubData.getFollowing());
            githubUser.setTotalStars(gitHubData.getTotalStars());
            githubUser.setTotalCommits(gitHubData.getTotalCommits());
            githubUserRepository.save(githubUser);

            // CodeStack 엔티티 생성 및 저장
//        @SuppressWarnings("unchecked")
            Map<String, Integer> languages = gitHubData.getLanguages();
            for (Map.Entry<String, Integer> entry : languages.entrySet()) {
                CodeStack codeStack = new CodeStack();
                codeStack.setUserIdx(user.getIdx());
                codeStack.setLanguage(entry.getKey());
                codeStack.setCodeCount(entry.getValue());
                codeStackRepository.save(codeStack);
            }

            // ... 레포, 외부 링크 또는 다른 엔티티 처리 ...

        } catch (Exception e) {
            log.error("Fail to save data.", e);
            throw e;

        }
    }
}
