package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.exception.CustomizeErrorCode;
import ren.aiernory.blog.exception.CustomizeException;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.PublishService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.ArticleController
 * @date 2020/3/18 14:34
 * @Description:
 */
@Controller
public class ArticleController {
    @Autowired
    private PublishService publishService;
    @Value("${sourceMusicPath}")
    private String sourcePath;
    
    @GetMapping("/article/{id}")
    public ModelAndView article(@PathVariable(name = "id") Integer id, ModelAndView modelAndView, HttpServletRequest request){
        Publish article = publishService.getById(id);
        if(article==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        modelAndView.addObject("article",article);
        modelAndView.setViewName("article");
        modelAndView.addObject("musicPath",sourcePath);
        return modelAndView;
    }
}
