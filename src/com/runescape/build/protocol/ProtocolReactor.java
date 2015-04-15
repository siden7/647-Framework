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
package com.runescape.build.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Optional;

import org.ivy.connect.GameReactor;
import org.ivy.connect.state.impl.CreationGameState;
import org.ivy.connect.state.impl.HandshakeGameState;
import org.ivy.connect.state.impl.LoginGameState;
import org.ivy.connect.state.impl.OndemandGameState;

import com.runescape.build.protocol.decode.CreationDecoder;
import com.runescape.build.protocol.decode.HandshakeDecoder;
import com.runescape.build.protocol.decode.LoginDecoder;
import com.runescape.build.protocol.decode.OndemandDecoder;
import com.runescape.build.protocol.encode.CreationEncoder;
import com.runescape.build.protocol.encode.HandshakeEncoder;
import com.runescape.build.protocol.encode.LoginEncoder;
import com.runescape.build.protocol.encode.OndemandEncoder;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 16, 2015
 */
public class ProtocolReactor extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.isReadable()) {
			/**
			 * The requested opcode sent from the client is used to determine what state we are using, protocol related.
			 * 
			 * <code>
			 * if (opcode == 14)
			 * 		// If the requested opcode is 14, this means we start directly with a login request.
			 * else if (opcode == 15)
			 * 		// If the requested opcode is 15, this means we start directly with a ondemand request.
			 * else if (opcode == 28)
			 * 		// If the requested opcode is 28, this means we start the account creation email request.
			 * </code>
			 */
			int opcode = in.readUnsignedByte();
			/**
			 * We set a specified enumeration type based on the opcode requested. We take advantage of the <i>Optional</i> utility class found inside the build in java library for deciding what
			 * request to use.
			 */
			Protocol request = Optional.of(opcode == 15 ? Protocol.REQUEST_HANDSHAKE : opcode == 28 ? Protocol.REQUEST_CREATION : Protocol.REQUEST_LOGIN).get();
			if (request == null) {
				return;
			}
			switch (request) {
			case REQUEST_HANDSHAKE:
				ctx.pipeline().addAfter("reactor", "handshake.encoder", new HandshakeEncoder());
				ctx.pipeline().replace("reactor", "handshake.decoder", bridgeGameState(ctx.channel(), new HandshakeDecoder()));

				/* We have to set the ondemand pipelines here since ondemand doesn't have a protocol opcode. */
				ctx.pipeline().addAfter("handshake.decoder", "ondemand.encoder", new OndemandEncoder());
				ctx.pipeline().replace("handshake.decoder", "ondemand.decoder", bridgeGameState(ctx.channel(), new OndemandDecoder()));
				break;
			case REQUEST_CREATION:
				/*
				 * We write a byte before changing the channel pipelines because we must write to the client that a pre-existing connection was successfully established.
				 */
				ctx.channel().writeAndFlush(Unpooled.buffer(1).writeByte(ProtocolResponse.SUCCESSFUL_LOGIN.getResponseId()));
				ctx.pipeline().addAfter("reactor", "creation.encoder", new CreationEncoder());
				ctx.pipeline().replace("reactor", "creation.decoder", bridgeGameState(ctx.channel(), new CreationDecoder()));
				break;
			case REQUEST_LOGIN:
				/* We write a byte here before changing the pipelines because we have to tell the client a connection established was successful. */
				ctx.channel().writeAndFlush(Unpooled.buffer(1).writeByte(ProtocolResponse.SUCCESSFUL.getResponseId()));
				ctx.pipeline().addAfter("reactor", "login.encoder", new LoginEncoder());
				ctx.pipeline().replace("reactor", "login.decoder", bridgeGameState(ctx.channel(), new LoginDecoder()));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Bridges together a requested {@code GameState}.
	 * 
	 * @param ctx The {@code ChannelHandlerContext} to use.
	 * @param decoder The requested {@code ByteToMessageDecoder} to use.
	 * @return The decoder used for bridging a {@code GameState}.
	 */
	public static ByteToMessageDecoder bridgeGameState(Channel channel, ByteToMessageDecoder decoder) {
		ProtocolRequest bridge = decoder.getClass().getAnnotation(ProtocolRequest.class);
		if (bridge == null) {
			return null;
		}
		switch (bridge.request()) {
		case REQUEST_HANDSHAKE:
			channel.attr(GameReactor.GAME_STATE).set(new HandshakeGameState(channel));
			break;
		case REQUEST_ONDEMAND:
			channel.attr(GameReactor.GAME_STATE).set(new OndemandGameState(channel));
			break;
		case REQUEST_CREATION:
			channel.attr(GameReactor.GAME_STATE).set(new CreationGameState(channel));
			break;
		case REQUEST_LOGIN:
			channel.attr(GameReactor.GAME_STATE).set(new LoginGameState(channel));
			break;
		case REQUEST_GAME:
			// channel.attr(GameReactor.GAME_STATE).set(new WorldGameState(channel));
			break;
		}
		return decoder;
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Feb 16, 2015
	 */
	public enum Protocol {
		REQUEST_HANDSHAKE, REQUEST_ONDEMAND, REQUEST_CREATION, REQUEST_LOGIN, REQUEST_GAME;
	}

}
