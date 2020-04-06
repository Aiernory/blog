package ren.aiernory.blog.service;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.mapper.LabelMapper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.service.LabelService;
import ren.aiernory.blog.service.PublishService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aiernory
 */

public interface SearchService {
    PageHelper searchByLabel(int page, int size, int order, int labelId);
    
    PageHelper searchByTitle(int page, int size, int order, String title);
    
    PageHelper search(Integer page, Integer size, Integer order, String search);
}
