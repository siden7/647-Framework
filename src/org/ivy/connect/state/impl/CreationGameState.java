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

import com.runescape.build.protocol.context.CreationRequestContext;
import com.runescape.build.protocol.context.CreationResponseContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 23, 2015
 */
public class CreationGameState extends GameState {

	/**
	 * Constructs a new {@code CreationGameState} {@code Object}.
	 * 
	 * @param channel The {@code Channel} to use.
	 */
	public CreationGameState(Channel channel) {
		super(channel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#channelRead(java.lang.Object)
	 */
	@Override
	public void channelRead(Object context) {
		if (context instanceof CreationRequestContext) {
			CreationRequestContext request = (CreationRequestContext) context;
			channel.writeAndFlush(new CreationResponseContext(request.getEmail()));
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
