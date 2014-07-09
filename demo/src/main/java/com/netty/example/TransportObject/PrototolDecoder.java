package com.netty.example.TransportObject;

import java.util.List;

import com.netty.example.utils.ByteBufToBytes;
import com.netty.example.utils.ByteObjConverter;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.ByteToMessageDecoder; 


public class PrototolDecoder extends ByteToMessageDecoder {
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {  
        ByteBufToBytes read = new ByteBufToBytes();  
        Object obj = ByteObjConverter.ByteToObject(read.read(in));  
        out.add(obj);  
    }  
  
} 
