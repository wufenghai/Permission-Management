package com.test.config;

import com.test.service.HelloService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类，用于自动配置HelloService
 */
@Configuration
//@EnableConfigurationProperties(value = HelloProperties.class)//启用HelloProperties这个配置属性  或者在该类上直接加@Component
public class HelloServiceAutoConfiguration {


    private HelloProperties helloProperties;

    //通过构造方法注入配置属性对象HelloProperties
    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @ConditionalOnMissingBean//spring没有该实例的时候再去创建
    @Bean
    public HelloService helloService() {
        return new HelloService(helloProperties.getName(), helloProperties.getAddress());
    }
}
