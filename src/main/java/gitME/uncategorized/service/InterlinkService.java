package gitME.uncategorized.service;

import gitME.global.util.JsonUtil;
import gitME.global.util.RestUtil;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class InterlinkService {
    GitHub github;

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

    public void getCommits(String token, String name) {
        try {
            connectToGithub(token);
        } catch (IOException e) {
            throw new IllegalArgumentException("github token 연결에 실패하였습니다.");
        }

        GHCommitSearchBuilder builder = github.searchCommits()
                .author(name);

        PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(7);
        int totalCommits = commits.getTotalCount();
        System.out.println("totalCommits: " + totalCommits);
    }

    private void connectToGithub(String token) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        github.checkApiUrlValidity();
    }
}
