package gitME.user.service;

import gitME.auth.dto.SignUpDataDTO;
import gitME.entity.CodeStack;
import gitME.entity.ExternalLink;
import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.entity.dto.GitHubDataDTO;
import gitME.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardDataService {
    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final CodeStackRepository codeStackRepository;
    private final ExternalLinkRepository externalLinkRepository;

    @Transactional
    public void saveData(SignUpDataDTO signUpDataDTO, GitHubDataDTO gitHubDataDTO) {
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
            githubUser.setNickname(gitHubDataDTO.getNickname());
            githubUser.setAvatarUrl(gitHubDataDTO.getAvatarUrl());
            githubUser.setFollowers(gitHubDataDTO.getFollowers());
            githubUser.setFollowing(gitHubDataDTO.getFollowing());
            githubUser.setTotalStars(gitHubDataDTO.getTotalStars());
            githubUser.setTotalCommits(gitHubDataDTO.getTotalCommits());
            githubUserRepository.save(githubUser);

            // CodeStack 엔티티 생성 및 저장
            Map<String, Integer> languages = gitHubDataDTO.getLanguages();
            for (Map.Entry<String, Integer> entry : languages.entrySet()) {
                CodeStack codeStack = new CodeStack();
                codeStack.setUserIdx(user.getIdx());
                codeStack.setLanguage(entry.getKey());
                codeStack.setCodeCount(entry.getValue());
                codeStackRepository.save(codeStack);
            }

            Map<String, String> externalLinks = signUpDataDTO.getExternalLink();
            for (Map.Entry<String, String> entry : externalLinks.entrySet()) {

                ExternalLink externalLink = new ExternalLink();
                externalLink.setUserIdx(user.getIdx());
                externalLink.setUrl(entry.getValue());
                externalLink.setDescription(entry.getKey());
                externalLinkRepository.save(externalLink);

            }



            // ... 레포, 외부 링크 또는 다른 엔티티 처리 ...

        } catch (Exception e) {
            log.error("Fail to save data.", e);
            throw e;

        }
    }
}
