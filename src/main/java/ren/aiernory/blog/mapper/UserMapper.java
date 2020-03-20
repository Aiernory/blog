package ren.aiernory.blog.mapper;

import ren.aiernory.blog.model.User;
import tk.mybatis.mapper.common.Mapper;

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
