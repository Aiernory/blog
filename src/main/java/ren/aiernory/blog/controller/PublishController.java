package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.PublishController
 * @date 2020/2/20 13:30
 * @Description:
 */
@Controller
public class PublishController {
    
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    
    
    
}
