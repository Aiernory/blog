package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HelloController
 * @date 2020/1/13 13:39
 * @Description:
 */
@Controller
public class HelloController {
    
    @GetMapping("hello")
    ModelAndView hello(ModelAndView model){
        model.setViewName("hello");
        
        return model;
        
    }
    
    
    
}
