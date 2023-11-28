package gitME.user.service;

import gitME.entity.CodeStack;
import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.repository.*;
import gitME.user.dto.totalInfoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final CodeStackRepository codeStackRepository;


    @Transactional
    public totalInfoDTO getInfo(int user_idx) {
        User user = userRepository.findById(user_idx).orElse(null);
        GithubUser gitUser = githubUserRepository.findById(user_idx).orElse(null);

        totalInfoDTO totalInfo = totalInfoDTO.builder()
                .kakaoId(user.getKakaoId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .phone(user.getPhone())
                .nickname(gitUser.getNickname())
                .followers(gitUser.getFollowers())
                .following(gitUser.getFollowing())
                .totalStars(gitUser.getTotalStars())
                .totalCommits(gitUser.getTotalCommits())
                .avatarUrl(gitUser.getAvatarUrl())
                .build();

        System.out.println(totalInfo);
        return totalInfo;
    }


    public void lang(int user_idx) {
        List<CodeStack> codeStacks = codeStackRepository.findByUserIdx(user_idx);
            Map<String, Integer> codeStackMap = new HashMap<>();
            for (CodeStack codeStack : codeStacks) {
                codeStackMap.put(codeStack.getLanguage(),
                        codeStack.getCodeCount());
            }
        }
}
