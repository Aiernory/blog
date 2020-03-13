package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.mapper.UserMapper;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.tool.CookieLogin;

import javax.annotation.Resource;
import javax.naming.Name;
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
    String index(ModelAndView model, HttpServletRequest request) {

       
        return "index";
    }
    
    
    
}
