package ren.aiernory.blog.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.User
 * @date 2020/2/10 16:34
 * @Description:
 */
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
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public Long getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    
    public Long getGmtModified() {
        return gmtModified;
    }
    
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
