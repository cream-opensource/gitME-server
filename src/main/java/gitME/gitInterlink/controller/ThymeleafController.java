package gitME.gitInterlink.controller;

import gitME.entity.*;
import gitME.gitInterlink.service.AuthService;
import gitME.gitInterlink.service.InterlinkService;
import gitME.testANDtest.JpaTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
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

}
