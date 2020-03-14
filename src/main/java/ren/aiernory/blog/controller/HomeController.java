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
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PublishService publishService;
    
    
    @GetMapping
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
    
    //@PostMapping("/setSize")
    //public Model setPageSize( @RequestParam(name = "size", defaultValue = "7") Integer size,Model model){
    //    //modelAndView每次都是新的，不能通过这个设置。也不想将页数写进数据库。大量数据不存session。加用cookie用thymeleaf也不方便。新定义pageHelper列表就空了。。
    //    PageHelper pageHelper = (PageHelper) model.getAttribute("pageHelper");
    //
    //    if(size<1){
    //        size=1;
    //    }
    //    pageHelper.setSize(size);
    //    model.addAttribute("pageHelper", pageHelper);
    //    return model;
    //}
    //
}
