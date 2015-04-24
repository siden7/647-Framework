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
import org.ivy.game.node.entity.player.Player;

import utilities.cryption.isaac.IsaacRandomBlueprint;

import com.runescape.build.packet.PacketRepository;
import com.runescape.build.packet.decode.PacketDecoder;
import com.runescape.build.protocol.context.GameRequestContext;
import com.runescape.build.protocol.context.GameResponseContext;
import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 22, 2015
 */
public class WorldGameState extends GameState {

	/**
	 * Represents the {@code Player} to use for this {@code WorldGameState}.
	 */
	private final Player player;

	/**
	 * Represents the {@code ISAACPair} to use.
	 */
	private final IsaacRandomBlueprint isaacPair;

	/**
	 * Constructs a new {@code WorldGameState} {@code Object}.
	 * 
	 * @param player The {@code Player} to use.
	 * @param channel The {@code Channel} to use.
	 */
	public WorldGameState(Player player, IsaacRandomBlueprint isaacPair, Channel channel) {
		super(channel);
		this.player = player;
		this.isaacPair = isaacPair;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.network.state.GameState#channelRead(java.lang.Object)
	 */
	@Override
	public void channelRead(Object context) {
		if (context instanceof GameRequestContext) {
			GameRequestContext request = (GameRequestContext) context;
			PacketDecoder packet = PacketRepository.getDecodingPacket(request.getIncomingPacket().getPacketId());
			if (packet == null) {
				player.getPacketProcessor().processConsoleMessage("Unhandled Packet: " + request.getIncomingPacket().getPacketId());
				return;
			}
			packet.decodePacket(player, request.getIncomingPacket());
		}
	}

	/**
	 * Writes a {@code IoWriteEvent} into the {@code Channel}.
	 * 
	 * @param buffer The {@code IoWriteEvent} to use.
	 */
	public void write(Class<?> clazz, IoWriteEvent buffer) {
		if (channel.isRegistered()) {
			synchronized (channel) {
				channel.writeAndFlush(new GameResponseContext(isaacPair, clazz, buffer));
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

	/**
	 * Gets the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Mar 25, 2015
	 */
	public enum GameStage {
		READ_PACKET, READ_SIZE, FINALIZE;
	}

}
