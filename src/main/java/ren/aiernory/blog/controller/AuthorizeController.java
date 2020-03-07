package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ren.aiernory.blog.dto.AccessTokenDTO;
import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.provider.GithubProvider;
import ren.aiernory.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.Authorize
 * @date 2020/1/14 21:13
 * @Description:
 */

@Controller
public class AuthorizeController {
    
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    
    
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        
        //没有cookie
        int n = 3;//超时登录重复次数
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String userToken;
        GithubUser githubUser;
        User user;
        while (n > 0) {
            //github登录
            userToken = githubProvider.getAccessToken(accessTokenDTO);
            githubUser = githubProvider.getUser(userToken);
            if (githubUser != null && githubUser.getId() != null) {
                //通过githubId检测是否存在
                user = userService.checkUser(githubUser);
                if (user == null || user.getId() == null) {
                    //没用户，新建,创建cookie
                    user = new User();
                    //githubUser获取到的信息
                    user.setAccountId(githubUser.getId());
                    user.setName(githubUser.getName());
                    user.setAvatarUrl(githubUser.getAvatarUrl());
                    user.setBio(githubUser.getBio());
                    //其他生成信息
                    String token = UUID.randomUUID().toString();
                    user.setToken(token);
                    user.setGmtCreate(System.currentTimeMillis());
                    user.setGmtModified(System.currentTimeMillis());
                    if (userService.addUser(user) == 1) {
                        //添加成功,写入cookie
                        Cookie cookie = new Cookie("token", token);
                        cookie.setMaxAge(3600 * 24 * 7);//7天cookie
                        response.addCookie(cookie);
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                } else {
                    //有用户，写入session,更新信息到和github上的一致
                    User modifiedUser = new User();
                    
                    modifiedUser.setId(user.getId());//id
                    modifiedUser.setGmtCreate(user.getGmtCreate());//createTime
                    modifiedUser.setAccountId(user.getAccountId());//accountId
                    modifiedUser.setBio(user.getBio());//bio
                    modifiedUser.setAvatarUrl(user.getAvatarUrl());//头像
                    
                    String token = UUID.randomUUID().toString();//不同浏览器登录了，刷新token，过期token作废
                    modifiedUser.setToken(token);//token
                    modifiedUser.setGmtModified(System.currentTimeMillis());//修改时间
                    modifiedUser.setName(githubUser.getName());//name,可能更新
                    
                    if (userService.UpdateUser(modifiedUser) == 1) {
                        //修改成功
                        request.getSession().setAttribute("user", modifiedUser);
                        //添加成功,写入cookie
                        Cookie cookie = new Cookie("token", token);
                        cookie.setMaxAge(3600 * 24 * 7);
                        response.addCookie(cookie);
                        break;
                    }
                }
            } else {
                //登录失败
                n--;//超时重复
            }
        }
        return "redirect:index";
    }
    
    
}
