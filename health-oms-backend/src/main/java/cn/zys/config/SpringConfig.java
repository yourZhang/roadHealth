package cn.zys.config;

import cn.zys.common.OftenFinalMessage;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: road-health
 * @description: springconfig
 * @author: xiaozhang6666
 * @create: 2020-09-21 16:07
 **/
@Configuration
@Import({CorsAutoConfig.class, DubboConsumerConfig.class, SpringMVCConfig.class, SecurityConfig.class, SpringSecurityInitConfig.class})
public class SpringConfig {

    //redis注入
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxWaitMillis(100 * 1000);
        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, OftenFinalMessage.Redis_Ip, OftenFinalMessage.Redis_Port,
                OftenFinalMessage.Redis_TiemOut, OftenFinalMessage.Redis_PassWord);
        return jedisPool;
    }

    //限制上传文件大小
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600);
        multipartResolver.setMaxInMemorySize(4096);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
