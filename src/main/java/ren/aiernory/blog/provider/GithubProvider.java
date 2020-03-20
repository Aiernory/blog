package ren.aiernory.blog.provider;


import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;
import ren.aiernory.blog.dto.AccessToken;
import ren.aiernory.blog.dto.GithubUser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.provider.GithubProvider
 * @date 2020/1/14 21:31
 * @Description: 提供github登录输的连接跳转服务
 */
@Component//仅加入到bean中
public class GithubProvider {
    private OkHttpClient okHttpClient;
    
    public GithubProvider() {
        //错误Connection reset的解决，设置okHttp超时时间10s
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }
    
    
    public String getAccessToken(AccessToken accessToken) {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
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
    
    
    public GithubUser getUser(String accessToken) {
        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accessToken)
                .build();
        try {
            String string = okHttpClient.newCall(request).execute().body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
