package ren.aiernory.blog.service.impl;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.mapper.SortMapper;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.SortService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.SortServiceImpl
 * @date 2020/4/2 19:40
 * @Description:
 */
@Service
public class SortServiceImpl implements SortService {
    

    @Resource
    private SortMapper sortMapper;
  
    @Override
    public int addArticleSort(int pId, List<Integer> sorts){
        return sortMapper.insertToArticleSort(pId,sorts);
    }
    
}
