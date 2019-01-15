package com.wang.guava;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

@Component
public class MyJob {

    @Resource
    private IdGeneratorUtil idGeneratorUtil;
	
	@Scheduled(initialDelay=1, fixedDelay=1)
//	@Scheduled( cron = "0/1 * * * * *")
	public void job1(){
        ConcurrentMap<String, Object> allCache = IdGeneratorUtil.getAllCache();
        if (allCache != null) {
            int size = allCache.keySet().size();
            // 如果缓存值数小于临界值
            if (size < IdGeneratorUtil.criticalValue) {
                for (int i = 0; i < (IdGeneratorUtil.maxSize - size); i++) {
//                    GuavaCacheUtil.s
                }
            }
        }
        System.err.println("-------定时任务1 执行----------" + IdGeneratorUtil.getIdQueue().size());
	}
	
	private Long createId() {
        StringBuilder sb = new StringBuilder();
        int randNo = new Random().nextInt(999999);
        return Long.valueOf(sb.append(System.currentTimeMillis()).append(randNo).toString());
    }
	
	
}
