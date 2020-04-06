package ren.aiernory.blog.mapper;

import org.apache.ibatis.annotations.Param;
import ren.aiernory.blog.model.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.CommentMapper
 * @date 2020/3/23 23:22
 * @Description:
 */
public interface CommentMapper extends Mapper<Comment> {
    Comment selectById(Integer parentId);
    
    Integer incComment(@Param("id") Integer id, @Param("time")Long time);
    
    Comment verify(Integer id);
}
