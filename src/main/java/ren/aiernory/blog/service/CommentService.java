package ren.aiernory.blog.service;

import ren.aiernory.blog.model.Comment;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.CommentService
 * @date 2020/3/24 11:02
 * @Description:
 */
public interface CommentService {
    /**
     * 添加评论，内部识别评论等级，相应处理
     * @param comment 评论对象
     * @return
     */
    Comment addComment(Comment comment);
    
}
