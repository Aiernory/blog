package ren.aiernory.blog.mapper;

import org.apache.ibatis.annotations.Param;
import ren.aiernory.blog.model.Publish;
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
    
    List<Publish> listAllWithUserByPage(@Param("page") int page,@Param("size")  int size);
    
    int getTotalCount();
}
