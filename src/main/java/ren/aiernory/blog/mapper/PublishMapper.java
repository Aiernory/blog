package ren.aiernory.blog.mapper;

import org.apache.ibatis.annotations.Param;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.PublishMapper
 * @date 2020/3/4 1:06
 * @Description:
 */
public interface PublishMapper extends Mapper<Publish> {
    List<Publish> listAllWithUser();
    
    
    //文章分页展示
    List<Publish> listAllWithUserByPage(@Param("page") int page, @Param("size") int size, @Param("order") int order);
    
    
    //文章总数
    int getTotalCount();
    
    //用户文章分页展示
    List<Publish> listByCreatorWithUserByPage(@Param("user") User user, @Param("page") int page, @Param("size") int size);
    
    
    //用户文章总数
    int getTotalCountByCreator(@Param("user") User user);
    
    //打开文章
    Publish selectByIdWithComments(Integer id);
    
    //获取文章内容与作者
    Publish selectByIdWithAuthor(Integer id);
    
    //检测文章存在否
    Integer verify(Integer id);
    
    //阅读数增加
    Integer incView(Integer id);
    
    //评论数增加
    Integer incComment(@Param("id") Integer id, @Param("time") Long time);
    
}
