package ren.aiernory.blog.mapper;

import ren.aiernory.blog.model.Label;
import tk.mybatis.mapper.common.Mapper;

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
}
