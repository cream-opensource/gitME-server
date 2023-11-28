package gitME.entity.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * File: GitRepositoryResponse.java
 * Author: making
 * Date: 2023-11-23
 * Desc: GitHub 사용자 저장소 정보 응답 VO
 */
@Getter @Setter
public class GitHubRepositoryResponseVO {

    private String name;         // 저장소 이름
    @SerializedName("full_name")
    private String fullName;     // 전체 저장소 이름
    @SerializedName("private")
    private boolean isPrivate;   // 비공개 저장소 여부
    @SerializedName("html_url")
    private String htmlUrl;      // 저장소 GitHub URL
    private String description;  // 저장소 설명
    private String language;     // 주 사용 언어
    @SerializedName("forks_count")
    private int forksCount;      // 포크 수
    @SerializedName("stargazers_count")
    private int stargazersCount; // 스타 수
    @SerializedName("watchers_count")
    private int watchersCount;   // 관찰자 수
    @SerializedName("pushed_at")
    private Date pushedAt;       // 마지막 푸시 시간
    @SerializedName("created_at")
    private Date createdAt;      // 생성일
    @SerializedName("updated_at")
    private Date updatedAt;      // 수정일
}
