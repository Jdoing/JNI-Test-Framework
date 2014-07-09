package com.netty.demo;
import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter
{

    private int totalCnt = 0;
    private String rawCommand = "";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.println("*** Channel read begin!");
        ByteBuf in = (ByteBuf) msg;
        try {
            String myMsg = in.toString(CharsetUtil.US_ASCII);
            System.out.println(myMsg);
            System.out.println("*** msg length: " + myMsg.length());
            rawCommand += myMsg;
            totalCnt += myMsg.length();
        } finally {
            in.release();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        System.out.print("*** Channel read complete!");
        System.out.println("*** total length: " + totalCnt);
        System.out.println("*** command received: " + rawCommand);

        JTFCommand command = JTFCommandFactory.generateCommandObject(rawCommand);
        String retMsg = command.execute();
        System.out.println("*** Msg that returned by ZS:" + retMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
