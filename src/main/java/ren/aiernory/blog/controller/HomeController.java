package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.service.PublishService;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.HomeController
 * @date 2020/3/6 19:30
 * @Description:
 */
@Controller

public class HomeController {
    @Autowired
    private PublishService publishService;
    
    
    @GetMapping({"/home","/","/index"})
    public ModelAndView changePage(ModelAndView modelAndView,
                                   @RequestParam(name = "page", defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", defaultValue = "7") Integer size) {
        if (size < 1) {
            //方式乱get，导致后台报错问题
            size = 1;
        }
        if (page < 1) {
            //方式乱get，导致后台报错问题
            page = 1;
        }
        PageHelper pageHelper = publishService.listPage(page, size);
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.setViewName("home");
        
        return modelAndView;
    }
}
