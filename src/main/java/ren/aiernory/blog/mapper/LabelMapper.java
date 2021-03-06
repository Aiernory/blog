package ren.aiernory.blog.mapper;

import ren.aiernory.blog.model.ArticleLabel;
import ren.aiernory.blog.model.Label;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.LabelMapper
 * @date 2020/3/29 19:51
 * @Description:
 */
public interface LabelMapper extends Mapper<Label> {

    
    String getNameById(Integer id);
    
    Integer checkName(String label);
    
    Integer addLabel(Label label);
    
    
    Integer incCount(Integer id);
    
    
    List<Integer> getLabelsByArticleId(Integer articleId);
    
    Integer insertToArticleLabel(ArticleLabel articleLabel);
    
    List<Integer> getArticlesByLabelId(Integer labelId);
    
    Integer getIdByName(String name);
}
