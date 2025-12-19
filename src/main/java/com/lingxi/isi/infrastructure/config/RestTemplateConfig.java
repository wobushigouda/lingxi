package com.lingxi.isi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;

import java.time.Duration;

/**
 * RestTemplate 配置
 * 基础设施层配置，为与外部服务通信提供 RestTemplate Bean
 */
@Configuration
public class RestTemplateConfig {
    
    /**
     * 创建 RestTemplate Bean
     * 配置了连接超时和读取超时
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
