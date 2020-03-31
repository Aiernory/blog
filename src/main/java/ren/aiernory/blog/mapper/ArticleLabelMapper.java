package ren.aiernory.blog.mapper;


import ren.aiernory.blog.model.ArticleLabel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.ArticleLabelMapper.xml
 * @date 2020/3/30 11:30
 * @Description:
 */

public interface ArticleLabelMapper extends Mapper<ArticleLabel> {
    
    List<Integer> getLabelsByArticleId(Integer articleId);
}
