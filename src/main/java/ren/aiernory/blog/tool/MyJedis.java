package ren.aiernory.blog.tool;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.sound.sampled.Port;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.tool.MyJedis
 * @date 2020/3/29 17:36
 * @Description:
 */
@Component
public class MyJedis extends Jedis {
    //创建bean调用
    @Value("${redis.jedis.singleUsing.host}")
    private static String host="localhost";
    @Value("${redis.jedis.singleUsing.port}")
    private static int port=6379;
    @Value("${redis.jedis.singleUsing.database}")
    private static int db=15;
    
    public MyJedis() {
        super(host, port);
        this.select(db);
    }
    


}





