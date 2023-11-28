package gitME.user;

import gitME.entity.CodeStack;
import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public User getUser(int user_idx){
        User user = userRepository.findById(user_idx).orElse(null);
        if (user != null) {
            String kakaoId = user.getKakaoId();
            String name = user.getName();
            String birthDate = user.getBirthDate();
            String email = user.getEmail();
            String phone = user.getPhone();
        }
    }

    @Transactional
    public GithubUser getGithibUser(int user_idx){
        GithubUser gitUser = githubUserRepository.findById(user_idx).orElse(null);
        if (gitUser != null) {
            String nickname = gitUser.getNickname();
            int followers = gitUser.getFollowers();
            int following = gitUser.getFollowing();
            int star = gitUser.getTotalStars();
            int commits = gitUser.getTotalCommits();
            String phone = gitUser.getAvatarUrl();
        }
    }

    @Transactional
    public Map<String, Integer> getCodeStack(int user_idx) {
        List<CodeStack> codeStacks = codeStackRepository.findByUserIdx(user_idx);

        Map<String, Integer> codeStackMap = new HashMap<>();
        for (CodeStack codeStack : codeStacks) {
            codeStackMap.put(codeStack.getLanguage(), codeStack.getCodeCount());
        }
        return codeStackMap;
    }
}
