package cn.zys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @program: road-health
 * @description: SpringConfig
 * @author: xiaozhang6666
 * @create: 2020-09-20 21:25
 **/
@Configuration
@Import({MyBatisConfig.class, DubboProviderConfig.class})
public class SpringConfig {

}
