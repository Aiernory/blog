package ren.aiernory.blog.service.impl;

import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.mapper.CommentMapper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.model.Comment;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.service.PublishService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    @Resource
    private CommentMapper commentMapper;
    
    
    @Override
    public int addPublish(Publish publish) {
        long gmtTime = System.currentTimeMillis();
        publish.setGmtCreate(gmtTime);
        publish.setGmtModified(gmtTime);
        
        return publishMapper.insertSelective(publish);
    }
    
    //自选的排序
    @Override
    public PageHelper listPage(int page, int size ,int order) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.setCurrentPage(page);
        pageHelper.setSize(size);
        pageHelper.setOrder(order);
        int totalCount = publishMapper.getTotalCount();
        pageHelper.setPageList(totalCount);
        
        List<Publish> publishes = publishMapper.listAllWithUserByPage((page - 1) * size, size,order);
        pageHelper.setPublishes(publishes);
        return pageHelper;
    }
    //默认的时间排序
    @Override
    public PageHelper listByCreator(User user, int page, int size) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.setCurrentPage(page);
        pageHelper.setSize(size);
        int totalCount = publishMapper.getTotalCountByCreator(user);
        pageHelper.setPageList(totalCount);
        
        List<Publish> publishes = publishMapper.listByCreatorWithUserByPage(user, (page - 1) * size, size);
        pageHelper.setPublishes(publishes);
        return pageHelper;
    }
    
    
    //打开文章
    @Override
    public Publish getByIdWithComments(Integer id) {
        Publish publish = publishMapper.selectByIdWithComments(id);
        //这里查到了所有的comment
        //在这里处理一下，解决好他们之间的父子关系
        List<Comment> comments = publish.getComments();
        //1. 找到里面parent=-1的，和！=-1的
        //2. 不等于-1的，根据值，添加到=-1的里面，按照时间排序
        //3. 将等于-1的重组织写入publish中
        
        
        //排序，创建按时间
        comments.sort((Comment c1,Comment c2)->{
            int i = c1.getId()-c2.getId();
            return i;
        });
        //选出child级
        Stream<Comment> streamCh = comments.stream().filter(comment -> comment.getParent() != -1);
        
        streamCh.forEach(commentChild -> {
            Integer parent = commentChild.getParent();
            for (Comment comment : comments) {
                if (comment.getParent()==-1&&parent.equals(comment.getId())) {
                    if (comment.getChildComment() == null) {
                        comment.setChildComment(new ArrayList<>());
                    }
                    comment.getChildComment().add(commentChild);
                    break;
                }
            }
        });
        //选出parent级
        comments.removeIf(comment -> comment.getParent() != -1);
        streamCh.close();
        return publish;
    }
    
    @Override
    public Publish getByIdWithAuthor(Integer id){
        return publishMapper.selectByIdWithAuthor(id);
    }
    
    
    
    @Override
    public int update(Publish publish) {
        publish.setGmtModified(System.currentTimeMillis());
        return publishMapper.updateByPrimaryKey(publish);
        
    }
    
    @Override
    public int incView(Integer id) {
        //为什么更新成功了、返回的是null
        publishMapper.incView(id);
        return 1;
    }
    
}
