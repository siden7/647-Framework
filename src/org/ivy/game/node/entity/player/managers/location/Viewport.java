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
package org.ivy.game.node.entity.player.managers.location;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.world.impl.GameWorld;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 25, 2015
 */
public class Viewport {

	/**
	 * Represents the local players inside the {@code Viewport}.
	 */
	private Player[] localPlayers;

	/**
	 * Gets the local players indexes.
	 */
	private int[] localIndexes;

	/**
	 * Gets the global players indexes.
	 */
	private int[] globalIndexes;

	/**
	 * Constructs a new {@code Viewport} {@code Object}.
	 */
	public Viewport() {
		localPlayers = new Player[GameWorld.MAX_PLAYER_CAP];
		localIndexes = new int[2500];
		globalIndexes = new int[GameWorld.MAX_PLAYER_CAP];
	}

	/**
	 * Gets the local players.
	 * 
	 * @return the localPlayers
	 */
	public Player[] getLocalPlayers() {
		return localPlayers;
	}

	/**
	 * Sets the local players.
	 * 
	 * @param localPlayers the localPlayers to set
	 */
	public void setLocalPlayers(Player[] localPlayers) {
		this.localPlayers = localPlayers;
	}

	/**
	 * Gets the local players indexes.
	 * 
	 * @return the localIndexes
	 */
	public int[] getLocalIndexes() {
		return localIndexes;
	}

	/**
	 * Sets the local players indexes.
	 * 
	 * @param localIndexes the localIndexes to set
	 */
	public void setLocalIndexes(int[] localIndexes) {
		this.localIndexes = localIndexes;
	}

	/**
	 * Gets the global players indexes.
	 * 
	 * @return the globalIndexes
	 */
	public int[] getGlobalIndexes() {
		return globalIndexes;
	}

	/**
	 * Sets the global players indexes.
	 * 
	 * @param globalIndexes the globalIndexes to set
	 */
	public void setGlobalIndexes(int[] globalIndexes) {
		this.globalIndexes = globalIndexes;
	}

}
