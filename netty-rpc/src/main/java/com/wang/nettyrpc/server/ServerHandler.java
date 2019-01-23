package com.wang.nettyrpc.server;

import com.wang.nettyrpc.common.ClassInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wp
 * @date 2019/1/19 11:49
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("当前线程：{}",Thread.currentThread().getName());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClassInfo classInfo = (ClassInfo) msg;
        Object clazz;
        if (!classMap.containsKey(classInfo.getClassName())){
            clazz = Class.forName(classInfo.getClassName()).newInstance();
            classMap.put(classInfo.getClassName(), clazz);
        } else {
            clazz = classMap.get(classInfo.getClassName());
        }
        Method method = clazz.getClass().getMethod(classInfo.getMethodName(), classInfo.getTypes());
        Object result = method.invoke(clazz, classInfo.getObjects());
        ctx.writeAndFlush(result);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
