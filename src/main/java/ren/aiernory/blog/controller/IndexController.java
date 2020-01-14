package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HelloController
 * @date 2020/1/13 13:39
 * @Description:
 */
@Controller
public class IndexController {
    
    @GetMapping("/")
    ModelAndView index(ModelAndView model){
        model.setViewName("index");
        
        return model;
    }
    
    
    
}
