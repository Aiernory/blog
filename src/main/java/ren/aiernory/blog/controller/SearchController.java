package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.service.SearchService;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.SearchController
 * @date 2020/4/4 12:33
 * @Description:
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    //输入框查询为 label+title
    //点击支持查询的label label
    //两个方法。至于将label和title分开，需要前端多一个选择框。。。。。。。。。完善点，三个方法
    //get还是post 参考一下别的网站  GET
    
    @Autowired
    private SearchService searchService;
    
    @GetMapping("/label/{label}")
    public ModelAndView searchByLabel(ModelAndView modelAndView,
                                      @PathVariable("label") String label,
                                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name = "size", defaultValue = "7") Integer size,
                                      @RequestParam(name = "order",defaultValue = "1") Integer order){
        
        //label  35:name
        String[] strings = label.split(":");
        
        //1. 数据库查找labelid，并返回文章的id号，最好一步到位，直线返回pageHelper
        PageHelper pageHelper = searchService.searchByLabel(page, size,order,Integer.parseInt(strings[0]));
        
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.addObject("sort","Search："+strings[1]);
        modelAndView.setViewName("home");
    
        return modelAndView;

    }
    @GetMapping("/title/{title}")
    public ModelAndView searchByTitle(ModelAndView modelAndView,
                                      @PathVariable("title") String title,
                                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name = "size", defaultValue = "7") Integer size,
                                      @RequestParam(name = "order",defaultValue = "1") Integer order){
        
  
        PageHelper pageHelper = searchService.searchByTitle(page, size,order,title);
    
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.addObject("sort","Search："+title);
        modelAndView.setViewName("home");
    
        return modelAndView;
    }
    @GetMapping("/{search}")
    public ModelAndView searchAll(ModelAndView modelAndView,
    @PathVariable("search") String search,
    @RequestParam(name = "page", defaultValue = "1") Integer page,
    @RequestParam(name = "size", defaultValue = "7") Integer size,
    @RequestParam(name = "order",defaultValue = "1") Integer order){
    
        PageHelper pageHelper = searchService.search(page, size,order,search);
    
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.addObject("sort","Search："+search);
        modelAndView.setViewName("home");
    
        return modelAndView;
    }
    
    
}
