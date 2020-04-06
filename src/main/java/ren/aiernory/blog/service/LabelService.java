package ren.aiernory.blog.service;

import ren.aiernory.blog.model.Label;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.LabelService
 * @date 2020/3/29 19:56
 * @Description:
 */
public interface LabelService {
    /**
     * 添加标签。根据名称朝招是否有标签（得到id），根据两个id，插入到关联表中
     * @param articleId 文章id
     * @param labelName 标签名称
     * @return
     */
    Integer addArticleLabel(Integer articleId, String labelName);
    
    /**
     * 读取标签。根据文章名，得到i其下全部label的name
     * @param articleId 文章id
     * @return
     */
    List<Label> getLabelNameByArticleId(Integer articleId);
    
    /**
     * 本service内部调用，根据id获取名字
     * @param id label 的 id
     * @return
     */
    String getNameById(Integer id);
    
    List<Integer> getArticlesByLabelId(Integer labelId);
    
    Integer getIdByName(String label);
}
