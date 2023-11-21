package gitME.user;

import gitME.common.util.JsonUtil;
import gitME.common.util.RestUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    public Map<String, String> getGitInfo(String accessToken) {
        String url = "https://api.github.com/user";

        String response = RestUtil.get(url, accessToken);
        Map<String, Object> gitInfoMap = JsonUtil.jsonObjectToMap(JsonUtil.parseJsonObjectString(response));

        String nickname = String.valueOf(gitInfoMap.get("login"));
        String avatarUrl = String.valueOf(gitInfoMap.get("avatar_url"));
        String followers = String.valueOf(gitInfoMap.get("followers"));
        String following = String.valueOf(gitInfoMap.get("following"));

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("nickname", nickname);
        resultMap.put("avatarUrl", avatarUrl);
        resultMap.put("followers", followers);
        resultMap.put("following", following);

        return resultMap;
    }
}
