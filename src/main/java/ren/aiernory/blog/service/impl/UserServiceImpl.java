package ren.aiernory.blog.service.impl;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.mapper.UserMapper;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.service.impl.UserServiceImpl
 * @date 2020/2/10 16:55
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    
  
    @Override
    public User login(GithubUser githubUser, HttpServletRequest request, HttpServletResponse response) {
        //验证cookie
        Cookie[] cookies = request.getCookies();
        User user;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.selectByToken(token);
                    if(user.getId()==null){
                        //cookie错误,返回github登录
                        return null;
                    }else {
                        return user;
                        //cookie正确，登陆成功
                    }
                }
            }
            //没有对应cookie,github登录
            return null;
        }else {
            //没有cookie
            return null;
        }
        
    }
    
    @Override
    public User checkUser(GithubUser githubUser) {
        User user;
        user  = userMapper.selectByAccountId(githubUser.getId());
        return user;
    }
    
    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }
    
    @Override
    public int UpdateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }
    
    
    @Override
    public User selectById(User user){
        return userMapper.selectByPrimaryKey(user);
    }
    
    @Override
    public User getUnPrivativeMessage(User user) {
        return userMapper.getUnPrivativeMessageById(user.getId());
    }
}
