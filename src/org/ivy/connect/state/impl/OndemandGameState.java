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

import utilities.ConsoleHandler;

import com.runescape.RuneScape;
import com.runescape.build.protocol.context.OndemandEncryptionContext;
import com.runescape.build.protocol.context.OndemandRequestContext;
import com.runescape.build.protocol.context.OndemandResponseContext;
import com.runescape.build.protocol.encode.OndemandEncryptionEncoder;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public class OndemandGameState extends GameState {

	/**
	 * Constructs a new {@code OndemandGameState} {@code Object}.
	 * 
	 * @param channel The {@code Channel} to use.
	 */
	public OndemandGameState(Channel channel) {
		super(channel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#channelRead(java.lang.Object)
	 */
	@Override
	public void channelRead(Object context) {
		if (context instanceof OndemandRequestContext) {
			OndemandRequestContext request = (OndemandRequestContext) context;
			try {
				if (request.getArchive() < 0) {
					return;
				}
				if (request.getContainer() != 0xff) {
					if (RuneScape.getGameContext().getCache().getStore().getIndexes().length <= request.getContainer() || RuneScape.getGameContext().getCache().getStore().getIndexes()[request.getContainer()] == null || !RuneScape.getGameContext().getCache().getStore().getIndexes()[request.getContainer()].archiveExists(request.getArchive())) {
						System.out.println(request.getContainer() + " : " + request.getArchive());
						return;
					}
				} else if (request.getArchive() != 0xff) {
					if (RuneScape.getGameContext().getCache().getStore().getIndexes().length <= request.getArchive() || RuneScape.getGameContext().getCache().getStore().getIndexes()[request.getArchive()] == null) {
						System.out.println(request.getContainer() + " : " + request.getArchive());
						return;
					}
				}
				channel.writeAndFlush(new OndemandResponseContext(request.getContainer(), request.getArchive(), request.isPriority()));
			} catch (Exception cause) {
				ConsoleHandler.handleException(OndemandGameState.class, cause);
			}
		} else if (context instanceof OndemandEncryptionContext) {
			OndemandEncryptionContext encryption = (OndemandEncryptionContext) context;
			OndemandEncryptionEncoder encoder = channel.pipeline().get(OndemandEncryptionEncoder.class);
			encoder.setEncryptedKey(encryption.getEncryptedKey());
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
