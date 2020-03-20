package ren.aiernory.blog.controller;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ren.aiernory.blog.dto.AccessToken;
import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.provider.GithubProvider;
import ren.aiernory.blog.service.UserService;
import ren.aiernory.blog.tool.CookieLogin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        String userToken;
        GithubUser githubUser;
        User user;
        while (n > 0) {
            //github登录
            userToken = githubProvider.getAccessToken(accessToken);
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
    
    

    //A浏览器登录中，B浏览器登录；A的cookie已经作废，A退出登录不再更新cookie
    //弹出一个小框，确定退出，一个选择，删除cookie（不自动登录）。
    @PostMapping("/logout")
    public String logout(@RequestParam(name = "delCookie",defaultValue = "off")String delCookie,
            HttpServletRequest request,
                          HttpServletResponse response){
        //退出登录状态
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        //cookie删除
        if("on".equals(delCookie)){
            Cookie cookie =new Cookie("token","");
            response.addCookie(cookie);
        }
        //把tcp链接关闭
        
        return "redirect:home";
    }
    
    
    
    @Autowired
    private CookieLogin cookieLogin;
    //前端页面链接地址
    @Value("${github.action.href}")
    private String GithubLoginHref;
    
  
    @GetMapping("/login")
    public String loginByCookie(HttpServletRequest request ,Model model,
                                HttpServletResponse response){
        //点一下登录，检测cookie，如果没有正确信息，跳转git授权链接
        //用Ajax
        //登录，cookie检测
        HttpSession session = request.getSession();
        cookieLogin.cookieVerify(request);
        if(session.getAttribute("user")==null){
            //访问github登录链接
            return "redirect:"+GithubLoginHref;
        }
        String referer = request.getHeader("referer");
        return "redirect:"+referer;
    }
}
