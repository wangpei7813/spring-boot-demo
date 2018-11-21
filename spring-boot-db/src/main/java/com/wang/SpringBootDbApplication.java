package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = {"com.wang.dao"})
public class SpringBootDbApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootDbApplication.class, args);
	}
}
