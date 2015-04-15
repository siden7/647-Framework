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

import org.ivy.connect.GameReactor;
import org.ivy.connect.state.GameState;
import org.ivy.game.node.entity.player.Account;
import org.ivy.game.node.entity.player.Player;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolReactor;
import com.runescape.build.protocol.ProtocolResponse;
import com.runescape.build.protocol.context.LoginRequestContext;
import com.runescape.build.protocol.context.LoginResponseContext;
import com.runescape.build.protocol.decode.GameDecoder;
import com.runescape.build.protocol.encode.GameEncoder;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 20, 2015
 */
public class LoginGameState extends GameState {

	/**
	 * Represents the {@code Player} to use for this {@code Session}.
	 */
	private Player player;

	/**
	 * Constructs a new {@code LoginGameState} {@code Object}.
	 * 
	 * @param channel The {@code Channel} to use.
	 */
	public LoginGameState(Channel channel) {
		super(channel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#channelRead(java.lang.Object)
	 */
	@Override
	public void channelRead(Object context) {
		if (context instanceof LoginRequestContext) {
			LoginRequestContext request = (LoginRequestContext) context;
			if (request.getMajorRevision() != RuneScape.getGameContext().getRevision()) {
				channel.writeAndFlush(new LoginResponseContext(request.getLoginRequest(), request.getUsername(), ProtocolResponse.OUT_OF_DATE));
			} else {
				channel.writeAndFlush(new LoginResponseContext(request.getLoginRequest(), request.getUsername(), ProtocolResponse.SUCCESSFUL_LOGIN));
			}

			channel.pipeline().addAfter("login.decoder", "game.encoder", new GameEncoder());
			channel.pipeline().replace("login.decoder", "game.decoder", ProtocolReactor.bridgeGameState(channel, new GameDecoder(request.getIsaacPair())));

			player = new Player(new Account(request.getUsername(), request.getPassword()));
			channel.attr(GameReactor.GAME_STATE).set(new WorldGameState(player, request.getIsaacPair(), channel));
			player.sendLogin((WorldGameState) channel.attr(GameReactor.GAME_STATE).get(), request);

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
