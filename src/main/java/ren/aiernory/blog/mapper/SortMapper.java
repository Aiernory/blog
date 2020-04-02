package ren.aiernory.blog.mapper;

import ren.aiernory.blog.model.Sort;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.SortMapper
 * @date 2020/4/1 19:21
 * @Description:
 */
public interface SortMapper extends Mapper<Sort> {
        //<!--根据名字，获取id-->
    int getSortId(String sort);
    //<!--根据s_id获取p_id数组-->
    List<Integer> getArticlesBySortId(int sortId);
    
    Sort getAllAsTree(int pId);
}
