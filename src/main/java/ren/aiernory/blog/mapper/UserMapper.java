package ren.aiernory.blog.mapper;

import org.springframework.stereotype.Repository;
import ren.aiernory.blog.dto.GithubUser;
import ren.aiernory.blog.model.User;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.mapper.UserMapper
 * @date 2020/2/10 16:26
 * @Description:
 */
public interface UserMapper extends Mapper<User> {
    
    
    User selectByAccountId(String accountId);
    User selectByToken(String token);
}
