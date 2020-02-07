package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ren.aiernory.blog.dto.AccessTokenDTO;
import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.provider.GithubProvider;

import javax.accessibility.AccessibleSelection;
import javax.servlet.http.HttpServletRequest;

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
    
    
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    
    
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){
        
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String userToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(userToken);
        System.out.println(user);
        request.getSession().setAttribute("user", "user");
        return "index";
    }
    
    
}
