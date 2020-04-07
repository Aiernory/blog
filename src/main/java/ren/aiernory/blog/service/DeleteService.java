package ren.aiernory.blog.service;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.mapper.CommentMapper;
import ren.aiernory.blog.mapper.LabelMapper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.model.Comment;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.DeleteService
 * @date 2020/4/7 16:05
 * @Description:
 */
@Service
public class DeleteService {
    //这是功能划分模块。。。。。....业务划分模块..懒得去改之前的功能了.........也不搞什么接口了...
    
    
    @Resource
    private LabelMapper labelMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private PublishMapper publishMapper;
    
    //public int delLabel(int labelId) {
    //    return labelMapper.deleteByPrimaryKey(labelId);
    //}
    
    public int delComments(List<Integer> CommentsId) {
        CommentsId.forEach(id->commentMapper.deleteByPrimaryKey(id));
        return 1;
    }
    
    
    public int delArticle(int id) {
        return  publishMapper.deleteByPrimaryKey(id);
    }
}
