package gitME.auth.github.service;

import com.google.gson.JsonObject;
import gitME.global.util.JsonUtil;
import gitME.global.util.RestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
@Transactional

public class GithubService {

    @Value("${GITHUB_CLIENT_ID}")
    private String githubClientId;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String githubClientSecret;

    public Map<String, String> getAccessToken(String code) {
        String url = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", githubClientId);
        body.add("client_secret", githubClientSecret);
        body.add("code", code);

        String response = RestUtil.post(url, body);
        JsonObject jsonObject = JsonUtil.parseJsonObjectString(response);

        return Map.of("AccessToken", jsonObject.get("access_token").getAsString());
    }

}
