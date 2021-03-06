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
                                   @RequestParam(name = "size", defaultValue = "7") Integer size,
                                   @RequestParam(name = "order",defaultValue = "1") Integer order) {
        /*
        数字order不容易出错
        1   创建时间降序
        2   create 升序
        3   modified 降序
        4   modified 升序
        5   commentCount 降序
        6   commentCount 升序
         */
 
        PageHelper pageHelper = publishService.listPage(page, size,order);
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.setViewName("home");
        
        return modelAndView;
    }
}
