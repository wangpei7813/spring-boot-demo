package com.wang.dbdemo.mapper;

import java.util.Map;

import com.wang.dbdemo.config.database.BaseMapper;
import com.wang.dbdemo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {
	
	Order shardSelectByPrimaryKey(@Param("tableName") String tableName, @Param("orderId") String orderId);
	
	void shardDeleteByPrimaryKey(@Param("tableName") String tableName, @Param("orderId") String orderId);
	
	int shardInsert(Map<String, Object> params);
	
	int shardUpdateByPrimaryKey(Map<String, Object> params);
}