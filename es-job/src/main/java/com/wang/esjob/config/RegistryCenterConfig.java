package com.wang.esjob.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${zookeeper.address}'.length() > 0")
public class RegistryCenterConfig {

    @Value("${zookeeper.address}")
    private String serverLists;

    @Value("${zookeeper.namespace}")
    private String namespace;

    @Value("${zookeeper.connectionTimeout}")
    private int connectionTimeout;

    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeout;

    @Value("${zookeeper.maxRetries}")
    private int maxRetries;
	
	/**
	 * 	把注册中心加载到spring 容器中
	 * @return
	 */
	@Bean(initMethod = "init")
	public ZookeeperRegistryCenter registryCenter() {
		ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverLists, namespace);
		zookeeperConfiguration.setConnectionTimeoutMilliseconds(connectionTimeout);
		zookeeperConfiguration.setSessionTimeoutMilliseconds(sessionTimeout);
		zookeeperConfiguration.setMaxRetries(maxRetries);
		
		return new ZookeeperRegistryCenter(zookeeperConfiguration);
		
	}
	
	
}