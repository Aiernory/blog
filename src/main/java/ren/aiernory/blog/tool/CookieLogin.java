package ren.aiernory.blog.tool;

import org.springframework.stereotype.Component;
import ren.aiernory.blog.mapper.UserMapper;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.tool.CookieLogin
 * @date 2020/3/6 2:01
 * @Description: 验证免登录cookie
 */
@Component
public class CookieLogin {
    @Resource
    private UserMapper userMapper;
    
    public void cookieVerify(HttpServletRequest request){
        //验证cookie
        Cookie[] cookies = request.getCookies();
        User user;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.selectByToken(token);
                    if (user!=null&&user.getId() != null) {
                        //cookie正确，直接登录
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
    }
    
}
