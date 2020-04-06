package ren.aiernory.blog.service;

import ren.aiernory.blog.dto.PageHelper;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.model.User;

import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.PublishService
 * @date 2020/3/4 1:05
 * @Description:
 */
public interface PublishService {
    /**
     * 添加文章
     * @param publish 文章对象
     * @return
     */
    int addPublish(Publish publish);
    
   
    /**
     * 打开文章时，包含很多信息。文章信息，作者信息。评论信息，和评论的作者信息
     * @param id 文章id
     * @return
     */
    Publish getByIdWithComments(Integer id);
    
    /**
     * 再次编辑操作时调用。get页面时获取信息。post页面时，验证，并且填充一些未修改的信息。
     * @param id 文章id
     * @return
     */
    Publish getByIdWithAuthor(Integer id);
    
    /**
     * 再次编辑时的具体写库操作
     * @param article
     * @return
     */
    int update(Publish article);
    
    /**
     * 每次打开文章增加阅读数
     * @param id
     * @return
     */
    int incView(Integer id);
    
    
    
    /**
     * 全部分类，展示一页
     * @param page  页号
     * @param size  页大小
     * @param order 排序
     * @return
     */
    PageHelper listPage(int page, int size,int order);
    
    /**
     * 用户文章分类，展示一页。采用默认排序。好像是id
     * @param user  用户对象
     * @param page  页号
     * @param size  页大小
     * @return
     */
    PageHelper listByCreator( int page, int size,int uId);
    
    /**
     * 这个方法可以迁移到publishService。然后对类似方法再做封装，减少重复     否定
     * 这个service不能太少东西，内部调用publishService，简化操作
     * 根据分类，查找到其下文章，列表形式
     * @param page  页号
     * @param size  页大小
     * @param order 顺序
     * @param sort  分类
     * @return
     */
    PageHelper listBySort(Integer page, Integer size, Integer order, String sort);
    
    //list文章信息大修改。一个基本方法，再一些其他方法，根据参数调用基本方法。基本方法参数最全
    /**
     * 文章列表的基本方法
     * @param page      页号
     * @param size      页大小
     * @param order     排序
     * @param uId       作者id（作者专栏）
     * @param ids       文章ids（分类信息使用，先查出id，没有的时候就是全部）
     * @return
     */
    PageHelper listPage(int page, int size, int order, int uId, List<Integer> ids);
    
    /**
     * 根据title 模糊查询ids
     * @param title
     * @return
     */
    List<Integer> getArticlesByTitle(String title);
}
