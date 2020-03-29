package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.resultMessage.ErrorMessage;
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
        Publish article = publishService.getByIdWithComments(id);
        if(article==null){
            throw new ErrorMessage(ErrorCodeEnum.QUESTION_NOT_FOUND);
        }
        //小改动，点击去的时候
        article.setViewCount(article.getViewCount()+1);
        modelAndView.addObject("article",article);
        modelAndView.setViewName("article");
        //阅读数增加,暂时每次请求都增加
        publishService.incView(id);
        modelAndView.addObject("musicPath",sourcePath);
        return modelAndView;
    }
}
