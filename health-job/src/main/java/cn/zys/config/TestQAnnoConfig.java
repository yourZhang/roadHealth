package cn.zys.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-27 10:26
 **/
@Configuration
@EnableScheduling
@ComponentScan("cn.zys.quartzs")
public class TestQAnnoConfig {
}
