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
package com.runescape.build.packet.context.impl;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.world.landscape.Location;

import com.runescape.build.packet.context.PacketContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 27, 2015
 */
public class LandscapePacketContext implements PacketContext {

	/**
	 * Represents the {@code Player} to use.
	 */
	private final Player player;

	/**
	 * Represents the {@code Location} to use.
	 */
	private final Location location;

	/**
	 * Represents if we are writing during login.
	 */
	private final boolean login;

	/**
	 * Constructs a new {@code LandscapePacketContext} {@code Object}.
	 * 
	 * @param player The {@code Player} to use.
	 * @param location The {@code Location} to use.
	 * @param login If we are writing this during login.
	 */
	private LandscapePacketContext(Player player, Location location, boolean login) {
		this.player = player;
		this.location = location;
		this.login = login;
	}

	/**
	 * Creates a new {@code LandscapePacketContext} context.
	 * 
	 * @param player The {@code Player} to use.
	 * @param location The {@code Location} to use.
	 * @param login The login to use.
	 * @return The created context.
	 */
	public static LandscapePacketContext createContext(Player player, Location location, boolean login) {
		return new LandscapePacketContext(player, location, login);
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
	 * Gets the login.
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets if during login.
	 * 
	 * @return the login
	 */
	public boolean isLogin() {
		return login;
	}

}
