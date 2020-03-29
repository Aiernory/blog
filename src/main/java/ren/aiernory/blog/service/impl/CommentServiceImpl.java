package ren.aiernory.blog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.mapper.CommentMapper;
import ren.aiernory.blog.mapper.PublishMapper;
import ren.aiernory.blog.model.Comment;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.service.CommentService;

import javax.annotation.Resource;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.CommentServiceImpl
 * @date 2020/3/24 11:02
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private PublishMapper publishMapper;
    
    @Value("${my.setting.project.access.url}")
    private String projectLocation;
    
    
    
    //事务管理，采用默认参数
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Comment addComment(Comment comment) {
        //三个行为：评论文章、评论楼层、楼层内的@型评论
        long modifiedTime = System.currentTimeMillis();
        //检测文章文章
        Integer article = publishMapper.verify(comment.getArticle());
        if (article == null) {
            throw new ErrorMessage(ErrorCodeEnum.COMMENT_PARENT_NOT_FOUND);
        } else {
            //文章存在
            if (comment.getParent() != -1) {
                //非文章主评论，判断
                Comment directComment = commentMapper.verify(comment.getParent());
                if (directComment == null) {
                    throw new ErrorMessage(ErrorCodeEnum.COMMENT_PARENT_NOT_FOUND);
                } else {
                    int mainCommentId;
                    //直接评论目标存在，看目标是不是主评论、或者楼层评论
                    if (directComment.getParent() == -1) {
                        //楼层评论\主评论
                        mainCommentId = directComment.getId();
                    } else {
                        //添加什么信息，到时候展示成为at.
                        //在哪里插入一段信息，告诉前端做成at
                        //添加新字段，、抑或者二级评论全部加上at，
                        //不能套娃。不然数据库查询又得麻烦半天
                        //@评论，存储的信息必须是主层评论的信息。只能考虑在content中加入字符串型的数据
                        //直接字符也需要一下直接目标的信息
                        //刚好前面有
                        
                        
                        String authorName = directComment.getAuthor().getName();
                        int authorId = directComment.getAuthor().getId();
                        String atTag="<a href=\"/profile/"+authorId+"/article\">@"+authorName+"</a>";
                        String newContent = atTag + comment.getContent();
                        comment.setContent(newContent);
                        
                        //子评论，转换成@at
                        //从directComment拿到user信息，写个string的a标签
                        //得到主评论
                        Comment mainComment = commentMapper.selectById(directComment.getParent());
                        mainCommentId = mainComment.getId();
                        //从三级目标，改为二级目标
                        comment.setParent(mainCommentId);
                       
                    }
                    //子评论存在。再判断，子评论所在的主评论是否存在..............乱七八糟...
                    
                    //事务节点1,主评论的评论数增加
                    commentMapper.incComment(mainCommentId, modifiedTime);
                }
            }
            //文章评论,文章已经确定存在
            //事务节点1，文章评论数增加
            
            publishMapper.incComment(comment.getArticle(),modifiedTime);
        }
        long gmtTime = System.currentTimeMillis();
        comment.setGmtCreate(gmtTime);
        comment.setGmtModified(gmtTime);
        //事务节点2
        commentMapper.insertSelective(comment);
        return comment;
        
    }
    
    
}
