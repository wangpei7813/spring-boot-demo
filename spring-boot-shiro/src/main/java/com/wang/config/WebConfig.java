package com.wang.config;

import com.wang.interceptor.ExcuteTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author wp
 * @date 2018/11/15 13:55
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExcuteTimeInterceptor()).addPathPatterns("/**");
    }
}
