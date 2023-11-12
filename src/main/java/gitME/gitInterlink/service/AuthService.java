package gitME.gitInterlink.service;

import com.google.gson.JsonObject;
import gitME.global.util.JsonUtil;
import gitME.global.util.RestUtil;
import gitME.repository.GithubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Transactional

public class AuthService {

    @Value("${client_id}")
    private String githubClientId;

    @Value("${client_secret}")
    private String githubClientSecret;

    public String getAccessToken(String code) {
        String url = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", githubClientId);
        body.add("client_secret", githubClientSecret);
        body.add("code", code);

        String response = RestUtil.post(url, body);
        JsonObject jsonObject = JsonUtil.parseJsonObjectString(response);

        System.out.println("jsonObject = " + jsonObject);
        return jsonObject.get("access_token").getAsString();
    }

}
