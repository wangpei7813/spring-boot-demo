package com.wang.esjob.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJobListener implements ElasticJobListener {

	private static Logger LOGGER = LoggerFactory.getLogger(SimpleJobListener.class);
	
	@Override
	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		LOGGER.info("-----------------执行任务之前：{}", shardingContexts);
	}

	@Override
	public void afterJobExecuted(ShardingContexts shardingContexts) {
		LOGGER.info("-----------------执行任务之后：{}", shardingContexts);		
	}
	
}