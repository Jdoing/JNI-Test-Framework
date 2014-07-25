package com.sandisk.jtf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class JTFServerInitializer extends ChannelInitializer<SocketChannel>
{
    private final ByteBuf delimiter;
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final JTFServerHandler SERVER_HANDLER = new JTFServerHandler();

    public JTFServerInitializer()
    {
        delimiter = Unpooled.buffer(2);
        delimiter.writeByte((byte) '\r');
        delimiter.writeByte((byte) '\n');
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception
    {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, delimiter));
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);
        pipeline.addLast(SERVER_HANDLER);
    }
}
