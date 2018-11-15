package com.wang.config;

import com.wang.interceptor.ExcuteTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @Description: 方法执行时间拦截器
     * @auther: wp
     * @date: 2018/11/15 10:37
     */
    @Bean
    ExcuteTimeInterceptor excuteTimeInterceptor() {
        return new ExcuteTimeInterceptor();
    }

    /**
     * 自定义拦截器配置
     * @Author wp
     * @date 2018/11/15 13:55
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(excuteTimeInterceptor()).addPathPatterns("/**");
    }
}
