package gitME.user;

import gitME.common.util.JsonUtil;
import gitME.common.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    GitHub github;

    private void connectToGithub(String token) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        github.checkApiUrlValidity();
    }

    public Map<String, Object> getGitAllInfo(String accessToken) {

        try {
            Map<String, String> gitBasicInfo = getGitBasicInfo(accessToken);
            int commitCount = getCommits(accessToken, gitBasicInfo.get("nickname"));
            Map<String, Object> repoLanguages = getLanguages(accessToken);
            int starCount = getStars(accessToken);

            Map<String, Object> allInfo = new HashMap<>();
            allInfo.putAll(gitBasicInfo);
            allInfo.put("commitCount", commitCount);
            allInfo.put("languages", repoLanguages);
            allInfo.put("starCount", starCount);

            return allInfo;
        } catch (Exception e) {
            log.error("getGitAllInfo: error", e);
            throw e;
        }

    }

    public Map<String, String> getGitBasicInfo(String accessToken) {
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

    public int getCommits(String accessToken, String name) {
        try {
            connectToGithub(accessToken);
        } catch (IOException e) {
            throw new IllegalArgumentException("github token 연결에 실패하였습니다.");
        }

        GHCommitSearchBuilder builder = github.searchCommits()
                .author(name);

        PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(10);
        return commits.getTotalCount();
    }

    public Map<String, Object> getLanguages(String accessToken) {
        String repoUrl = "https://api.github.com/user/repos";
        String repoResponse = RestUtil.get(repoUrl, accessToken);
        List<Map<String, Object>> repos = JsonUtil.jsonArrayToMapList(JsonUtil.parseJsonArrayString(repoResponse));

        Map<String, Object> aggregatedLanguages = new HashMap<>();

        for (Map<String, Object> repo : repos) {
            String repoName = (String) repo.get("full_name");
            String url = "https://api.github.com/repos/" + repoName + "/languages";
            String response = RestUtil.get(url, accessToken);

            Map<String, Object> gitLangMap = JsonUtil.jsonObjectToMap(JsonUtil.parseJsonObjectString(response));

            for (Map.Entry<String, Object> entry : gitLangMap.entrySet()) {
                String language = entry.getKey();
                Number count = (Number) entry.getValue();

                aggregatedLanguages.merge(language, count, (oldValue, newValue) -> ((Number) oldValue).doubleValue() + ((Number) newValue).doubleValue());
            }
        }

        return aggregatedLanguages;
    }


    public int getStars(String accessToken) {
        String url = "https://api.github.com/user/starred";

        String response = RestUtil.get(url, accessToken);
        List<Map<String, Object>> gitInfoMap = JsonUtil.jsonArrayToMapList(JsonUtil.parseJsonArrayString(response));

        return gitInfoMap.size();
    }

}
