package com.wang.nettyrpc.client;

import com.wang.nettyrpc.common.ClassInfo;
import com.wang.nettyrpc.common.MarshallingCodeCFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.Proxy;

/**
 * @author wp
 * @date 2019/1/22 11:45
 */
public class RPCClient {
    public static <T> T create(Object target) {

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {

            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassName(target.getClass().getName());
            classInfo.setMethodName(method.getName());
            classInfo.setObjects(args);
            classInfo.setTypes(method.getParameterTypes());

            ResultHandler resultHandler = new ResultHandler();
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();

                b.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline()
                                        .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                                        .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                                        .addLast(resultHandler);
                            }
                        });

                ChannelFuture f = b.connect("127.0.0.1", 8765).syncUninterruptibly();
                f.channel().writeAndFlush(classInfo);
                f.channel().closeFuture().sync();
            } finally {
                group.shutdownGracefully();
            }

            return resultHandler.getResponse();
        });
    }
}
