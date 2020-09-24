package cn.zys.config;

import cn.zys.common.OftenFinalMessage;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: road-health
 * @description: DubboProviderConfig
 * @author: xiaozhang6666
 * @create: 2020-09-20 21:16
 **/
@Configuration
@EnableDubbo(scanBasePackages = "cn.zys.service")
@ComponentScan("cn.zys")
public class DubboProviderConfig {

    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(3000);
        return providerConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("health-service-provider");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(OftenFinalMessage.Host_Zookeeper_Addr);
        registryConfig.setPort(OftenFinalMessage.Host_Zookeeper_Prot);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(OftenFinalMessage.Protocol_Port);
        return protocolConfig;
    }
}
