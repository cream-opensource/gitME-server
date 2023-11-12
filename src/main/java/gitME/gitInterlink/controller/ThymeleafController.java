package gitME.gitInterlink.controller;

import gitME.entity.*;
import gitME.gitInterlink.service.AuthService;
import gitME.gitInterlink.service.InterlinkService;
import gitME.testANDtest.JpaTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ThymeleafController {

    private final AuthService authService;
    private final InterlinkService interlinkService;
    private final JpaTestService jpaTestService;

    @Value("${client_id}")
    private String githubClientId;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("githubClientId", githubClientId);
        return "/home";
    }

    @GetMapping("/gitInterlink")
    public String gitInterlink(Model model, @RequestParam String code) {
        String accessToken = authService.getAccessToken(code);

        List<Map<String, Object>> repositories = interlinkService.getUserRepositories(accessToken);

        List<Map<String, Object>> langList = interlinkService.getLangByRepo(accessToken, repositories);

        model.addAttribute("repositories", repositories);
        model.addAttribute("langList", langList);

        return "/gitInterlink"; // test
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> findUser() {
        return ResponseEntity.ok(jpaTestService.findAllUser());
    }

    @GetMapping("/githubUser")
    public ResponseEntity<List<GithubUser>> findGithubUser() {
        return ResponseEntity.ok(jpaTestService.findAllGithubUser());
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(jpaTestService.saveUser(user));
    }

    @PostMapping("/githubUser")
    public ResponseEntity<GithubUser> saveGithubUser(@RequestBody GithubUser githubUser) {
        return ResponseEntity.ok(jpaTestService.saveGithubUser(githubUser));
    }

    @PostMapping("/repository")
    public ResponseEntity<Repository> saveRepository(@RequestBody Repository repository) {
        return ResponseEntity.ok(jpaTestService.saveRepository(repository));
    }

    @PostMapping("/codeStack")
    public ResponseEntity<CodeStack> saveCodeStack(@RequestBody CodeStack codeStack) {
        return ResponseEntity.ok(jpaTestService.saveCodeStack(codeStack));
    }

    @PostMapping("/externalLink")
    public ResponseEntity<ExternalLink> saveExternalLink(@RequestBody ExternalLink externalLink) {
        return ResponseEntity.ok(jpaTestService.saveExternalLink(externalLink));
    }
}
