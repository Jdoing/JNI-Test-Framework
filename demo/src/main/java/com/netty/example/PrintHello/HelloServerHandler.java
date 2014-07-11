package com.netty.example.PrintHello;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import com.netty.utils.*;

public class HelloServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Log.logInfo(">>>>>> I'm server.");
		// System.out.println(">>>>>> I'm server.");
		String msg = "Hello world\n";
		ByteBuf encoded = ctx.alloc().buffer(msg.length());
		encoded.writeBytes(msg.getBytes());
		ctx.write(encoded);
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//Log.logInfo("server receive message:" + msg);
		// System.out.println("服务器收到的消息:" + msg);
		ByteBuf in = (ByteBuf) msg;
		try {
			if (in.isReadable()) { // (1)
				String str = in.toString(CharsetUtil.US_ASCII);
				Log.logInfo("server receive message:" + str);
			}
		} finally {
			ReferenceCountUtil.release(msg); // (2)
		}

	}
}
