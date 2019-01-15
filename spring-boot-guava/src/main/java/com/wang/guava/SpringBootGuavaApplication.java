package com.wang.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@SpringBootApplication
@EnableScheduling
public class SpringBootGuavaApplication {

    @Bean
    public IdGeneratorUtil guavaCacheUtil() {
        return new IdGeneratorUtil();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGuavaApplication.class, args);
	}

}

