package ren.aiernory.blog.dto;

import lombok.Data;

/**
 * @author Aiernory
 * @className: com.test.dto.GithubUser
 * @date 2020/1/31 19:20
 * @Description: Github User的暂存对象
 */
@Data
public class GithubUser {
    private String name;
    private String id;
    private String bio;
    private String avatarUrl;
}
