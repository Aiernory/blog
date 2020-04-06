package ren.aiernory.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.mapper.CommentMapper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.mapper.SortMapper;
import ren.aiernory.blog.model.ArticleLabel;
import ren.aiernory.blog.model.Comment;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.service.PublishService;
import ren.aiernory.blog.service.SortService;

import javax.annotation.Resource;
import javax.lang.model.type.MirroredTypeException;
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
    @Resource
    private SortMapper sortMapper;
    
    @Override
    public int addPublish(Publish publish) {
        long gmtTime = System.currentTimeMillis();
        publish.setGmtCreate(gmtTime);
        publish.setGmtModified(gmtTime);
        return publishMapper.insertSelective(publish);
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
        comments.sort((Comment c1, Comment c2) -> {
            int i = c1.getId() - c2.getId();
            return i;
        });
        //选出child级
        Stream<Comment> streamCh = comments.stream().filter(comment -> comment.getParent() != -1);
        
        streamCh.forEach(commentChild -> {
            Integer parent = commentChild.getParent();
            for (Comment comment : comments) {
                if (comment.getParent() == -1 && parent.equals(comment.getId())) {
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
    public Publish getByIdWithAuthor(Integer id) {
        return publishMapper.selectByIdWithAuthor(id);
    }
    
    @Override
    public int update(Publish publish) {
        publish.setGmtModified(System.currentTimeMillis());
        return publishMapper.updateByPrimaryKey(publish);
        
    }
    
    
    //listPage 方法组
    
    @Override
    public int incView(Integer id) {
        //为什么更新成功了、返回的是null
        publishMapper.incView(id);
        return 1;
    }
    
    //默认的时间排序
    //根据作者，列出文章列表
    @Override
    public PageHelper listByCreator(int page, int size, int uId) {
        return this.listPage(page, size, 1, uId, null);
    }
    
    //自选的排序
    @Override
    public PageHelper listPage(int page, int size, int order) {
        return this.listPage(page, size, order, 0, null);
    }
    
    @Override
    public PageHelper listBySort(Integer page, Integer size, Integer order, String sort) {
        
        //根据sort查询到一个publish的id数组
        int sortId = sortMapper.getSortId(sort);
        List<Integer> articles = sortMapper.getArticlesBySortId(sortId);
        return this.listPage(page, size, order, 0, articles);
    }
    
    
    @Override
    public PageHelper listPage(int page, int size, int order, int uId, List<Integer> ids) {
        PageHelper pageHelper = new PageHelper();
        int totalCount;
        List<Publish> publishes;
      
        
        
        //参数控制：前置
        if (size < 1) {
            size = 7;
        }
        pageHelper.setSize(size);
        if (page < 0) {
            page = 1;
        }
        pageHelper.setCurrentPage(page);
    
        if (order < 1 || order > 6) {
            order = 1;
        }
        pageHelper.setOrder(order);
        
        //查询分为3种
        //1.    无限制
        //2.    限制creator
        //3.    限制 in（）
        //有些地方用到的信息并不一样，分开写。
        if (uId != 0) {
            //指定了用户
            totalCount = publishMapper.getTotalCountByCreator(uId);
            pageHelper.setPageList(totalCount);

            if (page > pageHelper.getMaxPage()) {
                page = 1;
            }
            publishes = publishMapper.listPageByCreator((page - 1) * size, size, order, uId);
        } else if (ids != null) {
            //制定了文章目标
            totalCount = ids.size();
            pageHelper.setPageList(totalCount);
        
            if (page > pageHelper.getMaxPage()) {
                page = 1;
            }
            //in （） 操作，sql不能容这个错误，多一步判断
            if(ids.size()>1){
                String articles = ids.toString().replace('[', '(').replace(']', ')');
                publishes = publishMapper.listPageBySelect((page - 1) * size, size, order, articles);
            }else {
                publishes=null;
            }
        } else {
            totalCount = publishMapper.getTotalCount();
            pageHelper.setPageList(totalCount);
 
            if (page > pageHelper.getMaxPage()) {
                page = 1;
            }
            publishes = publishMapper.listPage((page - 1) * size, size, order);
        }
        
        pageHelper.setPublishes(publishes);
        return pageHelper;
    }
    
    @Override
    public List<Integer> getArticlesByTitle(String title) {
        return publishMapper.getArticlesByTitle(title);
    }
    
}
