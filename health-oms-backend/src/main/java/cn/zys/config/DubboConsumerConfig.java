package cn.zys.config;

import cn.zys.common.OftenFinalMessage;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: road-health
 * @description: 消费者配置
 * @author: xiaozhang6666
 * @create: 2020-09-21 16:14
 **/
@Configuration
@EnableDubbo(scanBasePackages = "cn.zys")
@ComponentScan(value = {"cn.zys"})
public class DubboConsumerConfig {
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("health-oms-backend");
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(OftenFinalMessage.Host_Zookeeper_Addr);
        registryConfig.setPort(OftenFinalMessage.Host_Zookeeper_Prot);
        return registryConfig;
    }
}
