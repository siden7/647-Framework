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
package com.runescape.build.packet;

import org.ivy.game.node.entity.player.Account.AccountType;
import org.ivy.game.node.entity.player.Player;
import org.ivy.game.world.impl.GameWorld;
import org.ivy.game.world.landscape.Location;

import com.runescape.build.packet.context.impl.ChatPacketContext;
import com.runescape.build.packet.context.impl.ConfigPacketContext;
import com.runescape.build.packet.context.impl.InterfacePacketContext;
import com.runescape.build.packet.context.impl.LandscapePacketContext;
import com.runescape.build.packet.context.impl.MessagePacketContext;
import com.runescape.build.packet.context.impl.PingPacketContext;
import com.runescape.build.packet.context.impl.PlayerUpdateContext;
import com.runescape.build.packet.context.impl.WindowPacketContext;
import com.runescape.build.packet.context.impl.WorldListPacketContext;
import com.runescape.build.packet.encode.impl.ChatPacketEncoder;
import com.runescape.build.packet.encode.impl.ConfigPacketEncoder;
import com.runescape.build.packet.encode.impl.InterfacePacketEncoder;
import com.runescape.build.packet.encode.impl.LandscapePacketEncoder;
import com.runescape.build.packet.encode.impl.MessagePacketEncoder;
import com.runescape.build.packet.encode.impl.PingPacketEncoder;
import com.runescape.build.packet.encode.impl.PlayerUpdateEncoder;
import com.runescape.build.packet.encode.impl.WindowPacketEncoder;
import com.runescape.build.packet.encode.impl.WorldListPacketEncoder;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 22, 2015
 */
public class PacketProcessor {

	/**
	 * Represents the {@code Player} to use for this {@code PacketProcessor}.
	 */
	private final Player player;

	/**
	 * Constructs a new {@code PacketProcessor} {@code Object}.
	 * 
	 * @param player The player to use.
	 */
	public PacketProcessor(Player player) {
		this.player = player;
	}

	/**
	 * Sends the ping packet.
	 */
	public void processPing() {
		player.getGameState().write(PingPacketEncoder.class, PacketRepository.encodePacket(PingPacketContext.createContext(30)));
	}

	/**
	 * Sends the config packet to the client. Configs are widely used in <i>RuneScape</i> to have many changes in different components such as interfaces or number values.
	 * 
	 * @param id The id of the config.
	 * @param value The value of the config.
	 */
	public void processConfig(int id, int value) {
		player.getGameState().write(ConfigPacketEncoder.class, PacketRepository.encodePacket(ConfigPacketContext.createContext(id, value)));
	}

	// /**
	// * Sends the global config packet to the client. Configs are widely used in <i>RuneScape</i> to have many changes in different components such as interfaces or number values.
	// *
	// * @return The created packet.
	// */
	// public IoHeapWriter processGlobalConfig(int id, int value) {
	// return PacketRepository.encodePacket(GlobalConfigPacketEncoder.class, GlobalConfigPacketContext.createContext(id, value));
	// }

	/**
	 * Sends the message packet into the game console.
	 *
	 * @param message The message to send.
	 */
	public void processConsoleMessage(String message) {
		player.getGameState().write(MessagePacketEncoder.class, PacketRepository.encodePacket(MessagePacketContext.createContext(message, 99)));
	}

	/**
	 * Sends the message packet into the game chat.
	 *
	 * @param message The message to send.
	 */
	public void processGameMessage(String message) {
		player.getGameState().write(MessagePacketEncoder.class, PacketRepository.encodePacket(MessagePacketContext.createContext(message, 0)));
	}

	/**
	 * Sends the world list packet to the client. This packet is used for specifically the {@link Lobby}. Once the world list is updated, the connected {@link Player} is gained the ability to request
	 * a {@link GameWorld} login.
	 * 
	 * @param update If the world list needs an update.
	 */
	public void processWorldList(boolean update) {
		player.getGameState().write(WorldListPacketEncoder.class, PacketRepository.encodePacket(WorldListPacketContext.createContext(update)));
	}

	// /**
	// * Dispatches the open website packet to the client.
	// *
	// * @param url The url of the website to open.
	// * @return The created packet.
	// */
	// public IoHeapWriter processWebsite(String url) {
	// return PacketRepository.encodePacket(WebsitePacketEncoder.class, WebsitePacketContext.createContext(url));
	// }

	/**
	 * Sends the window frame packet to the client. This packet is used for displaying the actual game screen for the user to use. Component interfaces are also sent to the window.
	 *
	 * @param windowId The window id of the window frame.
	 * @param windowType The type of window.
	 */
	public void processWindow(int windowId, int windowType) {
		player.getGameState().write(WindowPacketEncoder.class, PacketRepository.encodePacket(WindowPacketContext.createContext(windowId, windowType)));
	}

	/**
	 * Sends the landscape packet to the client. This packet is used for displaying the connected {@link Player}'s current region's landscape. This is usually used multiple times due to region updates
	 * requested.
	 *
	 * @param location The {@code Location} of the landscape to use.
	 * @param login If this is being sent during login.
	 */
	public void processLandscape(Location location, boolean login) {
		player.getGameState().write(LandscapePacketEncoder.class, PacketRepository.encodePacket(LandscapePacketContext.createContext(player, location, login)));
	}

	/**
	 * Sends the interface packet to the client. This packet is used for multiple reasons in <i>RuneScape</i> such as displaying content related uses, or simple sending to the game window frame.
	 *
	 * @param windowId The window id to send the interface on.
	 * @param interfaceId The interface to send.
	 * @param childId The child id of the window.
	 * @param walkable If the interface closes when the player does an action.
	 */
	public void processInterface(int windowId, int interfaceId, int childId, boolean walkable) {
		player.getGameState().write(InterfacePacketEncoder.class, PacketRepository.encodePacket(InterfacePacketContext.createContext(windowId, interfaceId, childId, walkable)));
	}

	/**
	 * Sends the player render update packet to the client. This packet is used for rendering the connected {@code Player}(s) inside the game world.
	 */
	public void processPlayerUpdate() {
		player.getGameState().write(PlayerUpdateEncoder.class, PacketRepository.encodePacket(PlayerUpdateContext.createContext(player)));
	}

	/**
	 * Sends the public chat game message packet to the client.
	 * 
	 * @param message The message of the chat.
	 * @param accountType The account type of the player sending the message.
	 * @param index The player index of the player sending the message.
	 * @param effect The chat effect.
	 */
	public void processChatMessage(String message, AccountType accountType, int index, int effect) {
		player.getGameState().write(ChatPacketEncoder.class, PacketRepository.encodePacket(ChatPacketContext.createContext(message, accountType, index, effect)));
	}

}
