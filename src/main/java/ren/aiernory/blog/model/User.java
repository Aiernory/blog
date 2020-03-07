package ren.aiernory.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.User
 * @date 2020/2/10 16:34
 * @Description:
 */
@Data
@Table(name = "user")//驼峰可省略
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")//MySQL 和 SQL Server 这样的关系数据库管理系统的自动递增字段
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
