package gitME.user;

import gitME.entity.GithubUser;
import gitME.entity.User;
import gitME.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final RepositoryRepository repositoryRepository;
    private final CodeStackRepository codeStackRepository;
    private final ExternalLinkRepository externalLinkRepository;

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
}
