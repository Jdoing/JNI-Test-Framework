package com.netty.example.TransportObject;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	private int port;

	public Client(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		// Client服务启动器 3.x的ClientBootstrap 改为Bootstrap，且构造函数变化很大，这里用无参构造。
		Bootstrap bootstrap = new Bootstrap();
		// 指定channel类型
		bootstrap.channel(NioSocketChannel.class);
		// 指定Handler
		bootstrap.handler(new ClientHandler());
		// 指定EventLoopGroup
		bootstrap.group(new NioEventLoopGroup());
		// 连接到本地的8000端口的服务端
		bootstrap.connect(new InetSocketAddress("127.0.0.1", 9088));
	}
}
