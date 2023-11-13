package gitME.testANDtest;

import gitME.entity.*;
import gitME.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaTestService {

    private final UserRepository userRepository;
    private final GithubUserRepository githubUserRepository;
    private final RepositoryRepository repositoryRepository;
    private final CodeStackRepository codeStackRepository;
    private final ExternalLinkRepository externalLinkRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public List<GithubUser> findAllGithubUser() {
        return githubUserRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public GithubUser saveGithubUser(GithubUser githubUser) {
        return githubUserRepository.save(githubUser);
    }

    public Repository saveRepository(Repository repository) {
        return repositoryRepository.save(repository);
    }

    public CodeStack saveCodeStack(CodeStack codeStack) {
        return codeStackRepository.save(codeStack);
    }

    public ExternalLink saveExternalLink(ExternalLink externalLink) {
        return externalLinkRepository.save(externalLink);
    }
}
