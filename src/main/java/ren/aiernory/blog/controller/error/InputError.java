package ren.aiernory.blog.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.error.InputError
 * @date 2020/3/14 17:21
 * @Description:
 */
@Controller
@RequestMapping("/error")
public class InputError {
 
    public String error1(){
        return "error";
    }
}
