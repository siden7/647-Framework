/*The MIT License (MIT)

Copyright (c) 2015 Ivy Development Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/
package org.ivy.connect;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.io.IOException;

import org.ivy.connect.state.GameState;

import utilities.ConsoleHandler;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 16, 2015
 */
public class GameReactor extends ChannelInboundHandlerAdapter {

	/**
	 * Represents the attachment to use for each {@code GameState}.
	 */
	public static final AttributeKey<GameState> GAME_STATE = AttributeKey.valueOf("NetworkHandler.attr");

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel. ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		ConsoleHandler.handleMessage(GameReactor.class, "Channel connected from address " + ctx.channel().remoteAddress().toString().split(":")[0].replace("/", ""));
		super.channelRegistered(ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelUnregistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		ConsoleHandler.handleMessage(GameReactor.class, "Channel disconnected from address " + ctx.channel().remoteAddress().toString().split(":")[0].replace("/", ""));
		super.channelUnregistered(ctx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {
			return;
		}
		ConsoleHandler.handleException(GameReactor.class, cause);
		super.exceptionCaught(ctx, cause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object context) throws Exception {
		if (ctx.channel().attr(GAME_STATE).get() != null) {
			((GameState) ctx.channel().attr(GAME_STATE).get()).channelRead(context);
		}
		super.channelRead(ctx, context);
	}

}
