package com.wang.nettyrpc;

import com.wang.nettyrpc.client.RPCClient;
import com.wang.nettyrpc.service.HelloRpc;
import com.wang.nettyrpc.service.impl.HelloRpcImpl;

/**
 * @author wp
 * @date 2019/1/22 15:12
 */
public class Main {
    public static void main(String[] args) {
        HelloRpc helloRpc = new HelloRpcImpl();
        helloRpc = RPCClient.create(helloRpc);

        System.err.println(helloRpc.hello("test_" + 1));
        System.err.println(helloRpc.hello("test_" + 2));
        System.err.println(helloRpc.hello("test_" + 3));

        /*for (int i = 0; i < 5; i++) {
            System.err.println(helloRpc.hello("test_" + i));
        }*/
    }
}
