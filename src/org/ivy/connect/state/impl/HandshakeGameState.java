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
package org.ivy.connect.state.impl;

import io.netty.channel.Channel;

import org.ivy.connect.state.GameState;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolResponse;
import com.runescape.build.protocol.context.HandshakeRequestContext;
import com.runescape.build.protocol.context.HandshakeResponseContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public class HandshakeGameState extends GameState {

	/**
	 * Constructs a new {@code HandshakeGameState} {@code Object}.
	 * 
	 * @param channel The {@code Channel} to use.
	 */
	public HandshakeGameState(Channel channel) {
		super(channel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#channelRead(java.lang.Object)
	 */
	@Override
	public void channelRead(Object context) {
		if (context instanceof HandshakeRequestContext) {
			HandshakeRequestContext request = (HandshakeRequestContext) context;
			if (request.getRevision() != RuneScape.getGameContext().getRevision()) {
				channel.writeAndFlush(new HandshakeResponseContext(ProtocolResponse.OUT_OF_DATE));
			} else {
				channel.writeAndFlush(new HandshakeResponseContext(ProtocolResponse.SUCCESSFUL));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#disconnect()
	 */
	@Override
	public void disconnect() {
	}

}
