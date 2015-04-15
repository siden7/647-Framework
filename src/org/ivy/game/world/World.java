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
package org.ivy.game.world;

import org.ivy.game.node.entity.EntityCollection;
import org.ivy.game.node.entity.player.Player;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 25, 2015
 */
public class World {

	/**
	 * Represents the maximum number of players allowed in the {@code GameWorld}.
	 */
	public static final int MAX_PLAYER_CAP = 2048;

	/**
	 * Represents a collection to use for the {@code Player}'s in the {@code GameWorld}.
	 */
	private final EntityCollection<Player> PLAYERS = new EntityCollection<Player>();

	/**
	 * Adds a {@code Player} into the {@code GameWorld}.
	 * 
	 * @param player The {@code Player} to use.
	 * @return If added or not.
	 */
	public boolean addPlayer(Player player) {
		return PLAYERS.add(player);
	}

	/**
	 * Gets if the {@code GameWorld} has a requested {@code Player}.
	 * 
	 * @param player The requested {@code Player}.
	 * @return <b>TRUE</b> if the world has the player.
	 */
	public boolean hasPlayer(Player player) {
		return PLAYERS.contains(player);
	}

	/**
	 * Gets the players.
	 * 
	 * @return the players
	 */
	public EntityCollection<Player> getPlayers() {
		return PLAYERS;
	}

}
