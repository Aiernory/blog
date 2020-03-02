package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.mapper.UserMapper;
import ren.aiernory.blog.model.User;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HelloController
 * @date 2020/1/13 13:39
 * @Description:
 */
@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;
    
    @GetMapping({"/index", "/"})
    ModelAndView index(ModelAndView model, HttpServletRequest request) {
        model.setViewName("index");
        //验证cookie
        Cookie[] cookies = request.getCookies();
        User user;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.selectByToken(token);
                    if (user.getId() != null) {
                        //cookie正确，直接登录
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return model;
    }
    
    
    
}
