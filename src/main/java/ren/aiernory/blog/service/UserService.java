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
    User login(GithubUser githubUser, HttpServletRequest request , HttpServletResponse response);
    
    User checkUser(GithubUser githubUser);
    
    int addUser(User user);
    int UpdateUser(User user);
    
    User selectById(User user);
    
    User getUnPrivativeMessage(User user);
}
