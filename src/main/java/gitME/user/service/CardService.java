package gitME.user.service;

import gitME.entity.*;
import gitME.repository.*;
import gitME.user.dto.CardVisibilityConfigDTO;
import gitME.user.dto.TotalInfoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final CodeStackRepository codeStackRepository;
    private final CardVisibilityConfigRepository cardVisibilityConfigRepository;
    private final ExternalLinkRepository externalLinkRepository;

    @Transactional
    public TotalInfoDTO getInfo(int userIdx) {
        User user = userRepository.findById(userIdx).orElse(null);
        GithubUser gitUser = githubUserRepository.findById(userIdx).orElse(null);

        List<CodeStack> codeStacks = codeStackRepository.findByUserIdx(userIdx);
        Map<String, Integer> codeStackMap = new HashMap<>();
        for (CodeStack codeStack : codeStacks) {
            codeStackMap.put(codeStack.getLanguage(), codeStack.getCodeCount());
        }

        List<ExternalLink> externalLinks = externalLinkRepository.findByUserIdx(userIdx);
        Map<String, String> externalLinkMap = new HashMap<>();
        for (ExternalLink externalLink : externalLinks) {
            externalLinkMap.put(externalLink.getUrl(), externalLink.getDescription());
        }

        TotalInfoDTO totalInfo = TotalInfoDTO.builder()
                .userIdx(user.getIdx())
                .kakaoId(user.getKakaoId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .phone(user.getPhone())
                .externalLink(externalLinkMap)
                .nickname(gitUser.getNickname())
                .followers(gitUser.getFollowers())
                .following(gitUser.getFollowing())
                .totalStars(gitUser.getTotalStars())
                .totalCommits(gitUser.getTotalCommits())
                .avatarUrl(gitUser.getAvatarUrl())
                .languages(codeStackMap)
                .build();

        return totalInfo;
    }

    @Transactional
    public void saveCardVisibilityConfig(CardVisibilityConfigDTO cardVisibilityConfigDto) throws Exception {
        try {
            CardVisibilityConfig cardVisibilityConfig = new CardVisibilityConfig();
            cardVisibilityConfig.setUserIdx(cardVisibilityConfigDto.getUserIdx());
            cardVisibilityConfig.setNameVisibility(cardVisibilityConfigDto.getNameVisibility());
            cardVisibilityConfig.setBirthDateVisibility(cardVisibilityConfigDto.getBirthDateVisibility());
            cardVisibilityConfig.setPhoneVisibility(cardVisibilityConfigDto.getPhoneVisibility());
            cardVisibilityConfig.setNicknameVisibility(cardVisibilityConfigDto.getNicknameVisibility());
            cardVisibilityConfig.setFollowersVisibility(cardVisibilityConfigDto.getFollowersVisibility());
            cardVisibilityConfig.setFollowingVisibility(cardVisibilityConfigDto.getFollowingVisibility());
            cardVisibilityConfig.setTotalStarsVisibility(cardVisibilityConfigDto.getTotalStarsVisibility());
            cardVisibilityConfig.setTotalCommitsVisibility(cardVisibilityConfigDto.getTotalCommitsVisibility());
            cardVisibilityConfig.setAvatarUrlVisibility(cardVisibilityConfigDto.getAvatarUrlVisibility());
            cardVisibilityConfig.setCodeStackVisibility(cardVisibilityConfigDto.getCodeStackVisibility());
            cardVisibilityConfig.setExternalLinkVisibility(cardVisibilityConfigDto.getExternalLinkVisibility());
            cardVisibilityConfigRepository.save(cardVisibilityConfig);

        } catch (Exception e) {
            log.error("saveCardVisibilityConfig: error", e);
            throw e;

        }
    }
}
