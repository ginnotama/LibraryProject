package com.demo.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 * 解决 cors 跨域问题
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("127.0.0.1").allowCredentials(true).allowedMethods("*").maxAge(3600);
    }
}
