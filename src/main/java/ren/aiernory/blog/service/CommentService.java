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
    
    Comment addComment(Comment comment);
    
}
