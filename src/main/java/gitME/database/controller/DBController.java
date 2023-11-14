package gitME.database.controller;

import gitME.database.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DBController {

    private final JpaTestService jpaTestService;

    @PostMapping("/signUp")
    public ResponseEntity<> saveSignUp() {
        System.out.println("통신완료 \n");
        return (ResponseEntity) ResponseEntity.ok();
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
