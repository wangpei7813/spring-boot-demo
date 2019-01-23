package com.wang.nettyrpc.service.impl;

import com.wang.nettyrpc.service.HelloRpc;

/**
 * @author wp
 * @date 2019/1/22 14:28
 */
public class HelloRpcImpl implements HelloRpc {

    @Override
    public String hello(String name) {
        return "hello "+name;
    }

}
