package com.wang.netty.marshalling;

import com.wang.netty.helloworld.ClientHandler;
import com.wang.netty.utils.GzipUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author wp
 * @date 2019/1/21 10:47
 */
public class Client {

    public static void main(String[] args) throws Exception {

        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .option(ChannelOption.SO_RCVBUF, 1024 * 32)
                .option(ChannelOption.SO_SNDBUF, 1024 * 32)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                                .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                                .addLast(new ClientHandler());
                    }
                });

        // 绑定端口并启动
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8765).syncUninterruptibly();

        for (int i = 0; i < 10; i++) {
            future.channel().writeAndFlush(create(i + 1));
        }

        future.channel().closeFuture().sync();

        workGroup.shutdownGracefully();

    }

    private static Request create(int seq) throws Exception {
        Request request = new Request();
        request.setId(seq + "");
        request.setName("named:" + seq);
        request.setRequestMessage("messaged:" + seq);
        String path = System.getProperty("user.dir") + File.separatorChar + "sources" + File.separatorChar + "001.jpg";
        FileInputStream fis = new FileInputStream(new File(path));
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();
        request.setAttachment(GzipUtils.gzip(data));
        return request;
    }
}


