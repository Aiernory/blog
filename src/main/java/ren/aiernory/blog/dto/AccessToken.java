package ren.aiernory.blog.dto;

import lombok.Data;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.dto.AccessTokenDto
 * @date 2020/1/14 21:59
 * @Description: accessToken数据传输对象
 */
@Data
public class AccessToken {
    
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
