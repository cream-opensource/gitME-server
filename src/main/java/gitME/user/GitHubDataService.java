package gitME.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gitME.common.util.RestUtil;
import gitME.entity.dto.GitHubDataDTO;
import gitME.entity.vo.GitHubRepositoryResponseVO;
import gitME.entity.vo.GitHubUserResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File: GitHubDataService.java
 * Desc: GitHub 데이터 조회 서비스 클래스
 */
@Slf4j
@Service
public class GitHubDataService {

    private static final Gson gson = new Gson();

    /**
     * GitHub 데이터 조회
     * @param accessToken GitHub 접근 토큰
     * @return GitHub 데이터
     */
    public GitHubDataDTO getData(String accessToken) throws Exception {
        try {
            // GitHub 사용자 저장소 정보 조회
            GitHubUserResponseVO gitHubUserResponseVO = getUser(accessToken);

            // GitHub 스타를 준 저장소 정보 조회
            int totalStars = getStarredRepositoryList(accessToken).size();

            // GitHub 사용자 총 커밋 수 조회
            int totalCommits = getCommitCount(accessToken, gitHubUserResponseVO);

            // GitHub 저장소 사용 언어 정보 모음 조회
            Map<String, Integer> aggregateLanguages = getAggregateLanguages(accessToken);

            return GitHubDataDTO.builder()
                    .nickname(gitHubUserResponseVO.getLogin())
                    .avatarUrl(gitHubUserResponseVO.getAvatarUrl())
                    .followers(gitHubUserResponseVO.getFollowers())
                    .following(gitHubUserResponseVO.getFollowing())
                    .totalStars(totalStars)
                    .totalCommits(totalCommits)
                    .languages(aggregateLanguages)
                    .build();
        } catch (Exception e) {
            log.error("Fail to connect GitHub or retrieve data", e);
            throw e;
        }
    }

    /**
     * GitHub 사용자 저장소 정보 조회
     * @param accessToken GitHub 접근 토큰
     * @return GitHub 사용자 저장소 정보
     */
    public GitHubUserResponseVO getUser(String accessToken) {
        try {
            String url = "https://api.github.com/user";
            String response = RestUtil.get(url, accessToken);
            return gson.fromJson(response, GitHubUserResponseVO.class);
        } catch (Exception e) {
            log.error("Fail to retrieve user from GitHub", e);
            throw e;
        }
    }

    /**
     * GitHub 사용자 저장소 정보 조회
     * @param accessToken GitHub 접근 토큰
     * @return GitHub 사용자 저장소 정보
     */
    public List<GitHubRepositoryResponseVO> getRepositoryList(String accessToken) {
        try {
            String url = "https://api.github.com/user/repos";
            String response = RestUtil.get(url, accessToken);
            return gson.fromJson(response, new TypeToken<List<GitHubRepositoryResponseVO>>() {}.getType());
        } catch (Exception e) {
            log.error("Fail to retrieve repository list from GitHub", e);
            throw e;
        }
    }

    /**
     * GitHub 스타를 준 저장소 정보 조회
     * @param accessToken GitHub 접근 토큰
     * @return GitHub 스타를 준 저장소 정보
     */
    public List<GitHubRepositoryResponseVO> getStarredRepositoryList(String accessToken) {
        try {
            String url = "https://api.github.com/user/starred";
            String response = RestUtil.get(url, accessToken);
            return gson.fromJson(response, new TypeToken<List<GitHubRepositoryResponseVO>>() {}.getType());
        } catch (Exception e) {
            log.error("Fail to retrieve starred repositories from GitHub", e);
            throw e;
        }
    }

    /**
     * GitHub 저장소 사용 언어 정보 조회
     * @param accessToken GitHub 접근 토큰
     * @param repository
     * @return GitHub 저장소 사용 언어 정보
     */
    private Map<String, Integer> getLanguagesFromRepository(String accessToken, GitHubRepositoryResponseVO repository) {
        try {
            String url = "https://api.github.com/repos/" + repository.getFullName() + "/languages";
            String response = RestUtil.get(url, accessToken);
            return gson.fromJson(response, new TypeToken<Map<String, Integer>>() {}.getType());
        } catch (Exception e) {
            log.error("Fail to retrieve languages from GitHub repository", e);
            throw e;
        }
    }

    /**
     * GitHub 저장소 사용 언어 정보 모음 조회
     * @param accessToken GitHub 접근 토큰
     * @return GitHub 저장소 사용 언어 정보 모음
     */
    public Map<String, Integer> getAggregateLanguages(String accessToken) {
        try {
            List<GitHubRepositoryResponseVO> repositoryList = getRepositoryList(accessToken);

            Map<String, Integer> aggregatedLanguages = new HashMap<>();
            for (GitHubRepositoryResponseVO repository : repositoryList) {
                Map<String, Integer> gitLangMap = getLanguagesFromRepository(accessToken, repository);
                gitLangMap.forEach((language, count) -> aggregatedLanguages.merge(language, count, Integer::sum));
            }
            return aggregatedLanguages;
        } catch (Exception e) {
            log.error("Fail to aggregate languages from GitHub repositories", e);
            throw e;
        }
    }

    /**
     * GitHub 사용자 총 커밋 수 조회
     * @param accessToken GitHub 접근 토큰
     * @param user
     * @return GitHub 사용자 총 커밋 수
     */
    public int getCommitCount(String accessToken, GitHubUserResponseVO user) throws IOException {
        try {
            GitHub github = new GitHubBuilder().withOAuthToken(accessToken).build();
            github.checkApiUrlValidity();
            GHCommitSearchBuilder commitSearch = github.searchCommits().author(user.getLogin());
            PagedSearchIterable<GHCommit> commits = commitSearch.list();
            return commits.getTotalCount();
        } catch (IOException e) {
            log.error("Fail to retrieve commit count from GitHub", e);
            throw e;
        }
    }
}
