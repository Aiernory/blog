package ren.aiernory.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.mapper.LabelMapper;
import ren.aiernory.blog.model.Publish;

import ren.aiernory.blog.model.Sort;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.service.PublishService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.test
 * @date 2020/2/11 14:41
 * @Description:
 */
@Controller
public class test {
    //制定了bean名称，按名称注入。
    @Resource
    private Jedis myJedis;
    @Resource
    private LabelMapper labelMapper;
    @Autowired
    private PublishService publishService;
    
    @GetMapping("newtest")
    public ModelAndView article(ModelAndView modelAndView, HttpServletRequest request) {
        Integer id = 44;
        Publish article = publishService.getByIdWithComments(id);
        if (article == null) {
            throw new ErrorMessage(ErrorCodeEnum.QUESTION_NOT_FOUND);
        }
        //小改动，点击去的时候
        article.setViewCount(article.getViewCount() + 1);
        modelAndView.addObject("article", article);
        modelAndView.setViewName("test");
        //阅读数增加,暂时每次请求都增加
        publishService.incView(id);
        return modelAndView;
    }
    
    
    @RequestMapping("test")
    String test() {
        //
        //myJedis.sadd("44:heahenga", "1");
        //myJedis.sadd("44:hga", "1");
        //myJedis.sadd("44:henga", "1");
        //myJedis.sadd("44:heajjnga", "1");
        //myJedis.sadd("44:heakkknga", "1");
        return "test";
    }
    
    @GetMapping("test2")
    String test2()
    {

        return "test2";
    }
    @PostMapping("test2")
    String posttest2(@RequestBody String body)
    {
       
        System.out.println(body);
        //body = "{"+body+"}";
        JSONArray array = JSONObject.parseArray(body);
        array.forEach(a->{
            System.out.println(a);
        });
        Sort[] sorts = new Sort[0];
        Sort[] sorts1 = array.toArray(sorts);
        Sort[] objects = (Sort[]) array.toArray();
        List<Integer> ids = new ArrayList();
        for (int i = 0; i < objects.length; i++) {
            ids.add(objects[i].getId());
            if(objects[i].getChildren()!=null){
                objects[i].getChildren().forEach(s->{
                    ids.add(s.getId());
                });
            }
        }
    
        System.out.println(ids);
        
        System.out.println(array);
        return "test2";
    //  [{"title":"一级2","id":2,"field":"","spread":true,"children":[{"title":"二级2-1","id":5,"field":"","spread":true}                                     ]}]
    //  [{"title":"一级2","id":2,"field":"","spread":true,"children":[{"title":"二级2-1","id":5,"field":"","spread":true},{"title":"二级2-2","id":6,"field":""}]}]
    }
    
    @RequestMapping("test1")
    String test1() {
        return "test1";
    }
    
    @RequestMapping("test3")
    String test3() {
        return "test3";
    }
    
    @RequestMapping("test4")
    ModelAndView test4(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("test4");
       
    
        modelAndView.addObject("json", "jsonString");
        
        return modelAndView;
    }
    
    @RequestMapping("test5")
    ModelAndView test5(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("test5");
      
        modelAndView.addObject("jdata", "jdata");
        request.getSession().setAttribute("jdata", "jdata");
        return modelAndView;
    }
    
    @RequestMapping("test6")
    String test6() {
        return "test6";
    }
}
