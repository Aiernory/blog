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
    @RequestMapping("test2")
    String test2(){
        return "test2";
    }
    @RequestMapping("test1")
    String test1(){
        return "test1";
    }
    @RequestMapping("test3")
    String test3(){
        return "test3";
    }
    @RequestMapping("test4")
    String test4(){
        return "test4";
    }
    @RequestMapping("test5")
    String test5(){
        return "test5";
    }
    @RequestMapping("test6")
    String test6(){
        return "test6";
    }
}
