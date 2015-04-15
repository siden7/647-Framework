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
package com.runescape.build.protocol.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import utilities.ConsoleHandler;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolResponse;
import com.runescape.build.protocol.context.HandshakeResponseContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public class HandshakeEncoder extends MessageToByteEncoder<HandshakeResponseContext> {

	/**
	 * Constructs a new {@code HandshakeEncoder} {@code Object}.
	 */
	public HandshakeEncoder() {
		super(HandshakeResponseContext.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, HandshakeResponseContext msg, ByteBuf out) throws Exception {
		/**
		 * See {@link ProtocolResponse.java} for the different protocol responses.
		 * 
		 * After decoding the handshake procedure, we usually write back to the client the state of the game server. If the connection proved successful, then the response id written would be 0.
		 */
		out.writeByte(msg.getResponseCode().getResponseId());
		if (RuneScape.getGameContext().isDebug()) {
			ConsoleHandler.handleMessage(HandshakeEncoder.class, "HandshakeEncoder.java\tResponse: " + msg.getResponseCode());
		}
		if (msg.getResponseCode().equals(ProtocolResponse.SUCCESSFUL)) {
			/**
			 * Because the connection established was successful, we than write these 27 integers to the client. These are essentially just update keys used for the protocol. These always change
			 * between revisions.
			 */
			final int[] UPDATE_KEYS = { 136, 79328, 55571, 46770, 24563, 299978, 44375, 0, 4173, 3527, 106948, 592376, 168621, 285904, 342578, 672083, 17771, 20376, 16339, 1244, 7590, 678, 119, 791053, 904241, 3931, 2974 };
			for (int key : UPDATE_KEYS) {
				out.writeInt(key);
			}
		}
	}

}
