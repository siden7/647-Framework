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
import org.ivy.game.node.entity.player.managers.PlayerManager;
import org.ivy.game.world.landscape.Location;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 28, 2015
 */
public class LocationManager extends PlayerManager {

	/**
	 * Represents the {@code Viewport}.
	 */
	private final Viewport viewport = new Viewport();

	/**
	 * Constructs a new {@code LandscapeManager} {@code Object}.
	 * 
	 * @param player Represents the {@code Player} to use.
	 */
	public LocationManager(Player player) {
		super(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.game.node.entity.player.managers.PlayerManager#refreshManager()
	 */
	@Override
	public void refreshManager() {
		player.getPacketProcessor().processLandscape(Location.DEFAULT_LOCATION, true);
	}

	/**
	 * Gets the view port.
	 * 
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return viewport;
	}

}
