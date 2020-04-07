package ren.aiernory.blog.configuration;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import redis.clients.jedis.Jedis;
import ren.aiernory.blog.mapper.SortMapper;
import ren.aiernory.blog.model.Sort;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.configuration.RedisConfig
 * @date 2020/3/29 16:26
 * @Description:
 */
@Configuration
public class MyBeansConfig {
    
    //创建bean调用
    @Value("${redis.jedis.singleUsing.host}")
    private String myJedisHost;
    @Value("${redis.jedis.singleUsing.port}")
    private int myJedisPort;
    @Value("${redis.jedis.singleUsing.database}")
    private int myJedisDb;
    
    //jedis..这样的话不用自定义类了..  这叫什么设计模式，完全继承一下，进行简易修改 ... 代理那一类的
    @Bean(name = "myJedis")
    public Jedis getJedis() {
        Jedis jedis = new Jedis(myJedisHost, myJedisPort);
        jedis.select(myJedisDb);
        return jedis;
    }
    
    
    //sort信息配置。修改的地方有：初始加载到session的侧边栏，publish的get请求页面
    @Resource
    SortMapper sortMapper;
    
    //两个string，一个带href、一个不带
    
    //这个 侧边栏。header页面。不能单独发送请求，需要放session。放cookie前端不太清楚。放model每个请求都要把信息加进去。4、5个页面..
    @Bean
    public Sort getSort( ){
        return sortMapper.getAllAsTree(-1);
    }
    @Bean(name = "sortJson")
    public String getSortJson(){
        //这里考虑一下，加个定时，多长时间重载一次，不和后台管理通信，一定程度兼容后台操作。
        
        Sort sort = this.getSort();
        String jsonString="";
        if (sort != null) {
            //model，中加入个sort的json
             jsonString = JSON.toJSONString(sort.getChildren());
            //href、id、name -》 id、name。删除href
            String regex ="(?<=(?:\\]))(?:,\"href\":\"[\\w*/]*\",)(?=(?:\"id\"))";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher = pattern.matcher(jsonString.replaceAll("name", "title"));
            jsonString = matcher.replaceAll(",");
        }
        return jsonString;
    }
    
    
}