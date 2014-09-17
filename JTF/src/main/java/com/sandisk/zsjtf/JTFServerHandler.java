/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.sandisk.zsjtf;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.sandisk.zsjtf.exec.ZSGetRangeExec;
import com.sandisk.zsjtf.global.ZSAdapter;
import com.sandisk.zsjtf.global.ZSAdapterFactory;
import com.sandisk.zsjtf.global.ZSAdapterManger;
import com.sandisk.zsjtf.global.ZSCommandExec;
import com.sandisk.zsjtf.global.ZSCommandExecFactory;
import com.sandisk.zsjtf.util.Log;

/**
 * Handles a server-side channel.
 */
@Sharable
public class JTFServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String rawCommand)
			throws Exception {
		try {
			Log.logInfo(rawCommand);
			JTFCommand command = JTFCommandFactory
					.generateCommandObject(rawCommand);

			// Command patter: JTFCommand is invoker, ZSCommandExec is command,
			// ZSAdapter is receiver
			ZSAdapterManger zsAdapterManager = new ZSAdapterFactory();

			ZSAdapter zsAdapter = zsAdapterManager.getZSAdapter(command);

			String ZSCommandExecName = command.getZSCommandExecName();

			ZSCommandExec zsCommandExec = ZSCommandExecFactory.createZSCommandExec(ZSCommandExecName, zsAdapter);

			command.setZSCommandExec(zsCommandExec);

			String retMsg = command.execute();
			ctx.writeAndFlush(retMsg);
		} catch (ClassNotFoundException e) {
			ctx.writeAndFlush("CLIENT_ERROR command not found or not implement yet");
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
