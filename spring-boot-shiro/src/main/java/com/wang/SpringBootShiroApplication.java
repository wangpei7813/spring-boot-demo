package com.wang;

import com.wang.interceptor.ExcuteTimeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.wang.dao"})
public class SpringBootShiroApplication {

	/**
	 * @Description: 方法执行时间拦截器
	 * @auther: wp
	 * @date: 2018/11/15 10:37
	 */
	@Bean
	ExcuteTimeInterceptor excuteTimeInterceptor() {
		return new ExcuteTimeInterceptor();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}
}
