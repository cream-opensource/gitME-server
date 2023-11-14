package gitME.github.controller;

import com.google.gson.JsonElement;
import gitME.github.service.GithubService;
import gitME.github.service.InterlinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github")
public class GitHubAuthController {

    private final GithubService githubService;
    private final InterlinkService interlinkService;

    @Value("${client_id}")
    private String githubClientId;

    @GetMapping("/login")
    public RedirectView login() {
        String url = "https://github.com/login/oauth/authorize?client_id=" + githubClientId;
        return new RedirectView(url);
    }

    @GetMapping("/callback")
    public JsonElement callback(@RequestParam("code") String code) {
        JsonElement accessToken = githubService.getAccessToken(code);
        System.out.println("AccessToken = " + accessToken);

        return accessToken; // test
    }

}
