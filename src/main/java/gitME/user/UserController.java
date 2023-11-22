package gitME.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService interlinkService;

    @GetMapping("/gitinfo/{assesstoken}")
    public void getGitInfo(@PathVariable("assesstoken") String token) {
        Map<String, String> gitInfo = interlinkService.getGitInfo(token);
        interlinkService.getCommits(token, gitInfo.get("nickname"));
        interlinkService.getLanguages(token);
        interlinkService.getStars(token);
    }
}
