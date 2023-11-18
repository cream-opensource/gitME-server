package gitME.uncategorized.service;

import gitME.global.util.JsonUtil;
import gitME.global.util.RestUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InterlinkService {

    public String getGitInfo(String accessToken) {
        String url = "https://api.github.com/user";

        String response = RestUtil.get(url, accessToken);
        Map<String, Object> gitInfoMap = JsonUtil.jsonObjectToMap(JsonUtil.parseJsonObjectString(response));

        String nickname = String.valueOf(gitInfoMap.get("login"));
        System.out.println("Login: " + nickname);

        String avatarUrl = String.valueOf(gitInfoMap.get("avatar_url"));
        System.out.println("Avatar URL: " + avatarUrl);

        int followers = ((Number) gitInfoMap.get("followers")).intValue();
        System.out.println("Followers: " + followers);

        int following = ((Number) gitInfoMap.get("following")).intValue();
        System.out.println("Following: " + following);


        return nickname;

    }
}
