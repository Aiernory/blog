package ren.aiernory.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.PublishService;

import javax.annotation.Resource;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.PublishServiceImpl
 * @date 2020/3/4 1:06
 * @Description:
 */
@Service
public class PublishServiceImpl implements PublishService {
    @Resource
    private PublishMapper publishMapper;
    
    
    @Override
    public int addPublish(Publish publish) {
        long gmtTime = System.currentTimeMillis();
        publish.setGmtCreate(gmtTime);
        publish.setGmtModified(gmtTime);
        
        return publishMapper.insertSelective(publish);
    }
}
