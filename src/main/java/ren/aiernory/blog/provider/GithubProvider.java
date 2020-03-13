package ren.aiernory.blog.provider;


import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import ren.aiernory.blog.dto.AccessToken;
import ren.aiernory.blog.dto.GithubUser;

import java.io.IOException;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.provider.GithubProvider
 * @date 2020/1/14 21:31
 * @Description: 提供github登录输的连接跳转服务
 */
@Component//仅加入到bean中
public class GithubProvider {
    
    OkHttpClient okHttpClient = new OkHttpClient();
    
    public String getAccessToken(AccessToken accessToken) {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            return okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public GithubUser getUser(String accessToken){
        Request request= new Request.Builder()
                .url("https://api.github.com/user?"+accessToken)
                .build();
        try {
            String string = okHttpClient.newCall(request).execute().body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
