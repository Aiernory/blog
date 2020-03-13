package ren.aiernory.blog.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.PublishService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HomeController
 * @date 2020/3/6 19:30
 * @Description:
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PublishService publishService;

    
    
    
    @GetMapping
    public ModelAndView changePage(ModelAndView modelAndView,
                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "7") Integer size) {
    
        if(size<1){
            //方式乱get，导致后台报错问题
            size=1;
        }
        PageHelper pageHelper = publishService.listPage(page, size);
    
        modelAndView.addObject("pageHelper",pageHelper);
        modelAndView.setViewName("home");
        
        return modelAndView;
    }
    
    

    
}
