package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.service.SortService;
import ren.aiernory.blog.service.PublishService;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.SelectController
 * @date 2020/4/1 18:35
 * @Description:
 */
@Controller
public class SortController {
    /*
    查找
        标签
        对话框中：标签加标题
     */
    
    
    
    /*
    标签查找
        模拟标签
            A
                a1
                a2
            B
                b1
                b2
                
            连接为 /sort/A
                   /sort/a1
            
            
            把标签和分类分离开
                
                新建一个分类表   sort           分类、文章对应表 article_sort
                id  name    parent          id      p_id    s_id
                1   ALL     -1
                2   A       1
                3   B       1
                4   a1      2
                5   a2      2
                
                分类、文章对应表
                分类只分类，不参与查询。标签是查询。
                这个类，待会改名sort
                
         场景模拟
            发布文章。提交时选择标签。publish，方法返回的是重定向到主页。所以sort逻辑也要在那边。
                    总之拿到sort的id。
                    添加文章后拿到文章id。将两个id组合写入库。
            修改类似发布。
            
            点击分类，获取列表
                home方法迁移过来
           
     */
    
    @Autowired
    private PublishService publishService;
    @Autowired
    private SortService sortService;
    
    @GetMapping("/sort/{sort}")
    public ModelAndView changePage(ModelAndView modelAndView,
                                   @PathVariable("sort") String sort,
                                   @RequestParam(name = "page", defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", defaultValue = "7") Integer size,
                                   @RequestParam(name = "order",defaultValue = "1") Integer order) {
        

        PageHelper pageHelper = publishService.listBySort(page, size,order,sort);
        modelAndView.addObject("pageHelper", pageHelper);
        modelAndView.addObject("sort", sort);
        modelAndView.setViewName("home");
        
        return modelAndView;
    }
    
    
}
