package ren.aiernory.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.clients.jedis.Jedis;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.configuration.RedisConfig
 * @date 2020/3/29 16:26
 * @Description:
 */
@Configuration
public class RedisConfig {

    //
    //@Bean
    //public LettuceConnectionFactory redisConnectionFactory() {
    //
    //    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
    //}
    //
    ///**
    // * Lettuce
    // */
    //@Bean
    //public RedisConnectionFactory lettuceConnectionFactory() {
    //    RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
    //            .master("mymaster")
    //            .sentinel("127.0.0.1", 26379)
    //            .sentinel("127.0.0.1", 26380);
    //    return new LettuceConnectionFactory(sentinelConfig);
    //}
    //
}