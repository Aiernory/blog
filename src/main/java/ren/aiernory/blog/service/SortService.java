package ren.aiernory.blog.service;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.mapper.SortMapper;
import ren.aiernory.blog.model.Publish;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.SortService
 * @date 2020/4/1 18:53
 * @Description:
 */

public interface SortService {

    
    /**
     * 创建文章时，插入sorts信息
     * @param pId   文章id
     * @param sorts 分类，list列表
     * @return
     */
    int addArticleSort(int pId, List<Integer> sorts);
}
