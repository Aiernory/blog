package ren.aiernory.blog.service;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.LabelService
 * @date 2020/3/29 19:56
 * @Description:
 */
public interface LabelService {
    int addArticleLabel(Integer articleId, String labelName);
    
    List<String> getLabelNameByArticleId(Integer articleId);
    
    String getNameById(Integer id);
    
    

}
