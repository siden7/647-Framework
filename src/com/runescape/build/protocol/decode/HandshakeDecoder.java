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
package com.runescape.build.protocol.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import utilities.ConsoleHandler;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolReactor.Protocol;
import com.runescape.build.protocol.ProtocolRequest;
import com.runescape.build.protocol.context.HandshakeRequestContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
@ProtocolRequest(request = Protocol.REQUEST_HANDSHAKE)
public class HandshakeDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.isReadable()) {
			/**
			 * This is the current client revision that is sent from the connected client. We usually handle this to ensure the revision supported by the game server matches the clients' revision.
			 * 
			 * <code>
			 * if (revision == 647)
			 * 		// Good to continue.
			 * else
			 * 		// Close connection.
			 * </code>
			 */
			int revision = in.readInt();
			if (RuneScape.getGameContext().isDebug()) {
				ConsoleHandler.handleMessage(HandshakeDecoder.class, "HandshakeDecoder.java\tRequested revision: " + revision);
			}
			out.add(new HandshakeRequestContext(revision));
		}
	}

}
