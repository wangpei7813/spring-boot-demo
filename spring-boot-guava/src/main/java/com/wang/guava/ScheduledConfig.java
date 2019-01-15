package com.wang.guava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * @author wp
 * @date 2019/1/12 15:44
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(schedulerThreadPool());
    }

    @Bean(destroyMethod="shutdown")
    public Executor schedulerThreadPool() {
        return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                r -> new Thread(r, "job-thread"),
                (r, executor) -> System.err.println("当前任务执行失败: " + r));
    }
}
