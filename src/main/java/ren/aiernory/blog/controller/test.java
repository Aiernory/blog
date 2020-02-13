package ren.aiernory.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.test
 * @date 2020/2/11 14:41
 * @Description:
 */
@Controller
public class test {
    
    @RequestMapping("test")
    String test(){
        return "test";
    }
    
    
}
