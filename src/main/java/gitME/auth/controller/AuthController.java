package gitME.auth.controller;

import gitME.auth.dto.KakaoDTO;
import gitME.auth.service.KakaoService;
import gitME.common.MsgEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class AuthController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(@RequestParam("code") String code) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(code);

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
    }
}