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
    PageHelper listBySort(Integer page, Integer size, Integer order, String sort);
}
