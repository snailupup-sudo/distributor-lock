package com.online.taxi.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yueyi2019
 */
@Component
public class RedisConfig {

    @Autowired
    RedisSentinelProperties properties;


    //以下为红锁
    @Bean(name = "redissonRed1")
    @Primary
    public RedissonClient redissonRed1(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed2")
    public RedissonClient redissonRed2(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6380").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed3")
    public RedissonClient redissonRed3(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6381").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed4")
    public RedissonClient redissonRed4(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6382").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed5")
    public RedissonClient redissonRed5(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6383").setDatabase(0);
        return Redisson.create(config);
    }
    //以上为红锁
    
    
    
    // 单个redis
    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    	StringRedisTemplate redisTemplate = new StringRedisTemplate();
    	redisTemplate.setConnectionFactory(redisConnectionFactory);
    	return redisTemplate;

    }
    
    /**
     * 单个redisson
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
    	Config config = new Config();
    	config.useSingleServer().setAddress("127.0.0.1:6379").setDatabase(0);
//    	config.useSingleServer().setAddress("192.168.110.102:6379").setDatabase(0);

    	return Redisson.create(config);
    }



//    @Bean
//    public Redisson redisson(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }
}
