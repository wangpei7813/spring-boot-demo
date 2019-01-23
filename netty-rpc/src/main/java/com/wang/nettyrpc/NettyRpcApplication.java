package com.wang.nettyrpc;

import com.wang.nettyrpc.client.RPCClient;
import com.wang.nettyrpc.service.HelloRpc;
import com.wang.nettyrpc.service.impl.HelloRpcImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class NettyRpcApplication {

	public static void main(String[] args) {
//		SpringApplication.run(NettyRpcApplication.class, args);

        HelloRpc helloRpc = new HelloRpcImpl();
        helloRpc = RPCClient.create(helloRpc);
        System.err.println(helloRpc.hello("rpc"));

	}

}

