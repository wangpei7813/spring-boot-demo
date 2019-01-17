package com.wang.dbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "bhz.order.mapper")
@ComponentScan(basePackages = {"com.wang.dbdemo.*", "com.wang.dbdemo.config.*"})
public class MainConfig {

}
