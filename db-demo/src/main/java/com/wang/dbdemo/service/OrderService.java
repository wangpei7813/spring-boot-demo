package com.wang.dbdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.wang.dbdemo.config.database.DBContextHolder;
import com.wang.dbdemo.config.database.SelectConnection;
import com.wang.dbdemo.entity.Order;
import com.wang.dbdemo.mapper.OrderMapper;
import com.wang.dbdemo.utils.FastJsonConvertUtil;
import com.wang.dbdemo.utils.Pair;
import com.wang.dbdemo.utils.SelectorUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @SelectConnection(readOnly = true, tableName = "order")
    public Order shardSelectByPrimaryKey(String uuid) {
        return orderMapper.shardSelectByPrimaryKey(DBContextHolder.getTableName(), uuid);
    }

    //	注解的作用: 就是对数据源的切换
    @SelectConnection(tableName = "order")
    public int shardInsert(String uuid, Order order) throws Exception {

        JSONObject json = FastJsonConvertUtil.convertObjectToJSONObject(order);
        Map<String, Object> params = FastJsonConvertUtil.convertJSONToObject(json, Map.class);
        params.put("tableName", DBContextHolder.getTableName());
        return orderMapper.shardInsert(params);
    }

    @SelectConnection
    public int shardUpdateByPrimaryKey(String uuid, Order tradeDetail) throws Exception {

        JSONObject json = FastJsonConvertUtil.convertObjectToJSONObject(tradeDetail);
        Map<String, Object> params = FastJsonConvertUtil.convertJSONToObject(json, Map.class);
        params.put("tableName", DBContextHolder.getTableName());
        return orderMapper.shardUpdateByPrimaryKey(params);
    }

}
