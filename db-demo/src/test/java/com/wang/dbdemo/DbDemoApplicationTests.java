package com.wang.dbdemo;

import com.wang.dbdemo.entity.Order;
import com.wang.dbdemo.service.OrderService;
import com.wang.dbdemo.utils.FastJsonConvertUtil;
import com.wang.dbdemo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbDemoApplicationTests {

    @Autowired
    private OrderService orderService;


    @Test
    public void shardInsertTest() throws Exception {
        String key = KeyUtil.generatorUUID();
        System.err.println("key: " + key);
        Date currentDateTime = new Date();
        Order order = new Order();
        order.setOrderType("7");
        order.setPkgPrice(new BigDecimal(100.0));
        order.setPlatformId("007");
        order.setPlatformOrderId("113333234567890");
        order.setPoiId("333399933");
        order.setRemark("订单备注");
        order.setCityId("190000");
        order.setSenderAddress("国贸");
        order.setSenderName("八斗鸡");
        order.setSenderPhone("8888888");
        order.setSenderLng(8743221);
        order.setSenderLat(1234233);

        order.setReceiverAddress("双井");
        order.setReceiverName("张三");
        order.setReceiverPhone("9999999");
        order.setReceiverLng(2929292);
        order.setReceiverLat(1232112);

        order.setOrderId(key);
        order.setCreateBy("sys-init");
        order.setCreateTime(currentDateTime);
        order.setUpdateBy("sys-init");
        order.setUpdateTime(currentDateTime);
        orderService.shardInsert(key, order);
    }

    @Test
    public void shardSelectTest() throws Exception {

        Order order = orderService.shardSelectByPrimaryKey("2db190a0-1a2e-11e9-b6d1-8cec4b5ff0e2");
        System.err.println(FastJsonConvertUtil.convertObjectToJSONObject(order));
    }

}

