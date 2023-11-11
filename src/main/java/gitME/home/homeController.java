package gitME.home;

import gitME.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class homeController {
    private final KakaoService kakaoService;

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        String url = kakaoService.getKakaoLogin();
        System.out.println(url);
        return url;
    }
}
    