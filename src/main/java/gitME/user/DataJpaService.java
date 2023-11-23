package gitME.user;


import gitME.auth.dto.SignUpDataDTO;
import gitME.entity.CodeStack;
import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataJpaService {
    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final RepositoryRepository repositoryRepository;
    private final CodeStackRepository codeStackRepository;
    private final ExternalLinkRepository externalLinkRepository;

    public void saveData(SignUpDataDTO signUpDataDTO, Map<String, Object> gitAllInfo) {
        // User 엔티티 생성 및 저장
        User user = new User();
        user.setKakaoId(signUpDataDTO.getKakaoId());
        user.setName(signUpDataDTO.getName());
        user.setBirthDate(signUpDataDTO.getBirthDate());
        user.setEmail(signUpDataDTO.getEmail());
        user.setPhone(signUpDataDTO.getPhone());
        user.setPhone(signUpDataDTO.getPhone());
        userRepository.save(user);

        // GitHubUser 엔티티 생성 및 저장
        GithubUser githubUser = new GithubUser();
        githubUser.setUserIdx(user.getIdx());
        githubUser.setAccessToken(signUpDataDTO.getGitAccessToken());
        githubUser.setNickname((String) gitAllInfo.get("nickname"));
        githubUser.setFollowers(((Number) gitAllInfo.get("followers")).intValue());
        githubUser.setFollowing(((Number) gitAllInfo.get("following")).intValue());
        githubUser.setTotalStars(((Number) gitAllInfo.get("starCount")).intValue());
        githubUser.setTotalCommits(((Number) gitAllInfo.get("commitCount")).intValue());
        githubUser.setAvatarUrl((String) gitAllInfo.get("avatarUrl"));
        githubUserRepository.save(githubUser);

        // CodeStack 엔티티 생성 및 저장
//        @SuppressWarnings("unchecked")
        Map<String, Integer> languages = (Map<String, Integer>) gitAllInfo.get("languages");
        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
            CodeStack codeStack = new CodeStack();
            codeStack.setUserIdx(user.getIdx());
            codeStack.setLanguage(entry.getKey());
            codeStack.setCodeCount(entry.getValue());
            codeStackRepository.save(codeStack);
        }

        // ... 레포, 외부 링크 또는 다른 엔티티 처리 ...
    }
}
