package ren.aiernory.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.model.Comment;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.service.CommentService;
import ren.aiernory.blog.service.PublishService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.CommentController
 * @date 2020/3/23 23:24
 * @Description:
 */
@Controller
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    @Autowired
    private PublishService publishService;
    
    /**
     * 发表评论
     * @param body
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public String post(@RequestBody String body, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new ErrorMessage(ErrorCodeEnum.NOT_LOGIN_COMMENT);
        }
        JSONObject obj = JSONObject.parseObject(body);
        Comment comment = new Comment();
     
        comment.setContent(obj.getString("test-editormd-html-code"));
        comment.setCreator(obj.getInteger("creator"));
        comment.setArticle(obj.getInteger("article"));
        comment.setParent(obj.getInteger("parent"));
    
        Comment newComment = commentService.addComment(comment);

        String jsonString = JSON.toJSONString(newComment);
        return jsonString;
    }
    
    
    //@ResponseBody
    //@PostMapping("/comment/getList")
    //public String getList(Integer id) {
    //    //唯一参数是页面id，在这里先调试渲染，获取所有的评论
    //
    //    /*
    //    Comment 属性
    //    Integer id;//自增
    //    Integer parentId;
    //    Integer type;
    //    Integer creator;
    //    Integer likeCount;//初始0
    //    String content;
    //    Long gmtCreate;//时间,与publish的设定保持方式一致，在service方法中设置
    //    Long gmtModified;//时间
    //     */
    //
    //    //方法应该有id
    //
    //    String jsonString = JSON.toJSONString(new Object());
    //    return jsonString;
    //}
}
