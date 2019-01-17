package com.wang.dbdemo.config.database;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
//@EnableTransactionManagement
@AutoConfigureAfter(value = {DataSourceConfig.class})
@Slf4j
public class MyBatisConfiguration {

	@Resource(name= "order1-master")
	private DataSource order1MasterDataSource;
	
	@Resource(name= "order2-master")
	private DataSource order2MasterDataSource;
	
	@Resource(name= "order1-slave")
	private DataSource order1SlaveDataSource;
	
	@Resource(name= "order2-slave")
	private DataSource order2SlaveDataSource;

	@Bean("dynamicDataSource")
	public DynamicDataSource roundRobinDataSourceProxy(){
		
		Map<Object, Object> targetDataSource = new HashMap<>();
		
		targetDataSource.put(DBContextHolder.DBType.ORDER1_MASTER, order1MasterDataSource);
		targetDataSource.put(DBContextHolder.DBType.ORDER2_MASTER, order2MasterDataSource);
		targetDataSource.put(DBContextHolder.DBType.ORDER1_SLAVE, order1SlaveDataSource);
		targetDataSource.put(DBContextHolder.DBType.ORDER2_SLAVE, order2SlaveDataSource);

		//	实例化动态数据源
		DynamicDataSource proxy = new DynamicDataSource();
		//	盛放所以需要切换的数据源
		proxy.setTargetDataSources(targetDataSource);
		//	设置默认的数据源
		proxy.setDefaultTargetDataSource(order1MasterDataSource);
		
		return proxy;
	}
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) {
		
		System.err.println("----------------执行--------------");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:com/wang/dbdemo/mapping/*.xml"));
			SqlSessionFactory sqlSessionFactory = bean.getObject();
			sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);
			return sqlSessionFactory;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	@Bean
//	public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) throws Exception {
//	     return new DataSourceTransactionManager(dynamicDataSource);
//	}

	
	
	
}
