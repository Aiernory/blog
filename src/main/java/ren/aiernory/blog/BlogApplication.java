package ren.aiernory.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


//扫描过滤器
@ServletComponentScan
//开启事务支持
@EnableTransactionManagement
//缓存
@EnableCaching

@MapperScan("ren.aiernory.blog.mapper")
@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
    
    
}

