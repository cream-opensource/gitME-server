package gitME.user;

import gitME.common.util.JsonUtil;
import gitME.common.util.RestUtil;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
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

        PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(10);
        int totalCommits = commits.getTotalCount();
        System.out.println("totalCommits: " + totalCommits);
    }

    private void connectToGithub(String token) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        github.checkApiUrlValidity();
    }

    public void getLanguages(String token) {
        String repoUrl = "https://api.github.com/user/repos";

        String repoResponse = RestUtil.get(repoUrl, token);
        List<Map<String, Object>> repos = JsonUtil.jsonArrayToMapList(JsonUtil.parseJsonArrayString(repoResponse));

        List<String> repoNames = new ArrayList<>();
        for (Map<String, Object> repo : repos) {
            repoNames.add((String) repo.get("full_name"));
        }

        for (String repoName : repoNames) {
            String url = "https://api.github.com/repos/" + repoName +"/languages";
            String response = RestUtil.get(url, token);

            Map<String, Object> gitLangMap = JsonUtil.jsonObjectToMap(JsonUtil.parseJsonObjectString(response));

            System.out.println(repoName + ": " + gitLangMap);
        }
    }

    public void getStars(String token) {
        String url = "https://api.github.com/user/starred";

        String response = RestUtil.get(url, token);
        List<Map<String, Object>> gitInfoMap = JsonUtil.jsonArrayToMapList(JsonUtil.parseJsonArrayString(response));

        System.out.println(gitInfoMap.size());
    }

}
