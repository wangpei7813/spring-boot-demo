package com.wang.dbdemo.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wp
 * @date 2019/1/17 9:55
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "order1-master")
    @Primary
    @ConfigurationProperties(prefix = "druid.order1-master")
    public DataSource order1MasterDataSource() throws SQLException {
        DataSource order1MasterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("============= order1-master: {} ================", order1MasterDataSource);
        return order1MasterDataSource;
    }

    @Bean(name = "order2-master")
    @ConfigurationProperties(prefix = "druid.order2-master")
    public DataSource order2MasterDataSource() throws SQLException {
        DataSource order2MasterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("============= order2-master: {} ================", order2MasterDataSource);
        return order2MasterDataSource;
    }

    @Bean(name = "order1-slave")
    @ConfigurationProperties(prefix = "druid.order1-slave")
    public DataSource order1SlaveDataSource() throws SQLException {
        DataSource order1SlaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("============= order1-slave: {} ================", order1SlaveDataSource);
        return order1SlaveDataSource;
    }

    @Bean(name = "order2-slave")
    @ConfigurationProperties(prefix = "druid.order2-slave")
    public DataSource order2SlaveDataSource() throws SQLException {
        DataSource order2SlaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("============= order2-slave: {} ================", order2SlaveDataSource);
        return order2SlaveDataSource;
    }
}
