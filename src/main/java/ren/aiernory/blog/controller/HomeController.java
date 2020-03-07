package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HomeController
 * @date 2020/3/6 19:30
 * @Description:
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping
    public String homePage() {
        return "home";
    }
    
}
