package cn.zys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: road-health
 * @description: springconfig
 * @author: xiaozhang6666
 * @create: 2020-09-21 16:07
 **/
@Configuration
@Import({CorsAutoConfig.class, DubboConsumerConfig.class, SpringMVCConfig.class})
public class SpringConfig {
}
