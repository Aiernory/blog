package ren.aiernory.blog.service.impl;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.mapper.LabelMapper;
import ren.aiernory.blog.model.ArticleLabel;
import ren.aiernory.blog.model.Label;
import ren.aiernory.blog.service.LabelService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.LabelServiceImpl
 * @date 2020/3/29 19:56
 * @Description:
 */
@Service
public class LabelServiceImpl implements LabelService {
    
    
    @Resource
    private LabelMapper labelMapper;
  
    
    //持久化一个文章的标签
    @Override
    public int addArticleLabel(Integer articleId, String labelName) {
        ArticleLabel articleLabel = new ArticleLabel();
        articleLabel.setPId(articleId);
        
   
        Integer id = labelMapper.checkName(labelName);
        if(id==null){
            Label label=new Label();
            label.setName(labelName);
            label.setCount(1);
            //检测label是否已经有了
            
            //没有,新增。并且count设置为1
            labelMapper.addLabel(label);
            id=label.getId();
        }else{
            //,有的话count++
            labelMapper.incCount(id);
        }
        //拿到了id
        articleLabel.setLId(id);
        return labelMapper.insertToArticleLabel(articleLabel);
    }
    
 
    
    //字面意思，通过文章id得到标签名字list
    @Override
    public List<String> getLabelNameByArticleId(Integer articleId) {
        List<String> names = new ArrayList<>();
        
        List<Integer> labelsId = labelMapper.getLabelsByArticleId(articleId);
        labelsId.forEach(
                id -> {
                    names.add(this.getNameById(id));
                }
        );
        return names;
    }
    
    //根据id，得到name
    @Override
    public String getNameById(Integer id) {
        return labelMapper.getNameById(id);
    }
    
 
}
