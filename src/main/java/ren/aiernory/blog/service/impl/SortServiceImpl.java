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
    private PublishMapper publishMapper;
    @Resource
    private SortMapper sortMapper;
    
    @Override
    public PageHelper listBySort(Integer page, Integer size, Integer order, String sort) {
        
        //根据sort查询到一个publish的id数组
        int sortId = sortMapper.getSortId(sort);
        List<Integer> articles = sortMapper.getArticlesBySortId(sortId);
        
        int totalCount=articles.size();
        
        PageHelper pageHelper = new PageHelper();
        pageHelper.setCurrentPage(page);
        pageHelper.setSize(size);
        pageHelper.setOrder(order);
        pageHelper.setPageList(totalCount);
        String articlesString = articles.toString().replace('[', '(').replace(']', ')');
        List<Publish> publishes = publishMapper.listAllWithUserByPageBySort((page - 1) * size, size, order,articlesString);
        pageHelper.setPublishes(publishes);
        return pageHelper;
        
    }
}
