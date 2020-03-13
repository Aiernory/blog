package ren.aiernory.blog.service;

import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.model.Publish;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.PublishService
 * @date 2020/3/4 1:05
 * @Description:
 */
public interface PublishService {
    int addPublish(Publish publish);
    
    PageHelper listPage(int page, int size);
    
}
