package com.test.alldemo.netty;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NettyServer {
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
}
