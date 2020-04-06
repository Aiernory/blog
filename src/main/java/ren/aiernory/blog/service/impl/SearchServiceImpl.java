package ren.aiernory.blog.service.impl;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.service.LabelService;
import ren.aiernory.blog.service.PublishService;
import ren.aiernory.blog.service.SearchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.SearchServiceImpl
 * @date 2020/4/7 0:39
 * @Description:
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private LabelService labelService;
    @Resource
    private PublishService publishService;
    
    
    @Override
    public PageHelper searchByLabel(int page, int size, int order, int labelId) {
        //根据名字找到label id。或者前台能直接发过来id。前台只有name..  不去修改了..
        //修改一下吧，减少查询
        List<Integer> articles = labelService.getArticlesByLabelId(labelId);
        return publishService.listPage(page, size, order, 0, articles);
    }
    
    @Override
    public PageHelper searchByTitle(int page, int size, int order, String title) {
        //根据名字找到label id。或者前台能直接发过来id。前台只有name..  不去修改了..
        //修改一下吧，减少查询
        List<Integer> articles = publishService.getArticlesByTitle(title);
        return publishService.listPage(page, size, order, 0, articles);
    }
    
    
    @Override
    public PageHelper search(Integer page, Integer size, Integer order, String search) {
        //结果这里还需要查询....
        //前端...不再改回去了 十分狗头
        Integer labelId = labelService.getIdByName(search);
        List<Integer> articles = labelService.getArticlesByLabelId(labelId);
        articles.addAll(publishService.getArticlesByTitle(search));
        return publishService.listPage(page, size, order, 0, articles);
    }
}
