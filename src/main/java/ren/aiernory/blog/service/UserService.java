package ren.aiernory.blog.service;

import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.UserService
 * @date 2020/2/10 16:46
 * @Description:
 */
public interface UserService {
    /**
     * 登录操作，返回用户信息。这个方法起始已经有很多的细节迁移到了别的地方。。。懒得改了
     * @param githubUser    github获取的User信息
     * @param request       验证cookie用到
     * @return
     */
    User login(GithubUser githubUser, HttpServletRequest request);
    
    /**
     * 检测？检测什么？   检测github用户是否已经在本站创立过信息。...
     * @param githubUser
     * @return
     */
    User checkUser(GithubUser githubUser);
    
    /**
     * 添加
     * @param user
     * @return
     */
    int addUser(User user);
    
    /**
     * 非cookie登录时，会根据github请求到的信息，更新信息。
     * @param user  github获取的信息封装对象
     * @return
     */
    int UpdateUser(User user);
    
    /**
     * 字面意思。应该直接用selective..
     * @param user
     * @return
     */
    User selectById(User user);
    
    /**
     * 获取一些非私密信息，这个controller/service太早了，还涉及到授权登录，真的懒得改了..
     * @param user
     * @return
     */
    User getUnPrivativeMessage(User user);
}
