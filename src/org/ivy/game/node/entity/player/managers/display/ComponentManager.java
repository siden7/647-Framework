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
package org.ivy.game.node.entity.player.managers.display;

import java.util.Map.Entry;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.player.managers.PlayerManager;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 10, 2015
 */
public class ComponentManager extends PlayerManager {

	/**
	 * Constructs a new {@code ComponentManager} {@code Object}.
	 * 
	 * @param player The {@code Player} to use.
	 */
	public ComponentManager(Player player) {
		super(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.game.node.entity.player.managers.PlayerManager#refreshManager()
	 */
	@Override
	public void refreshManager() {
		for (Entry<Integer, int[]> i : ComponentElements.getComponents().entrySet()) {
			open(i.getKey(), i.getValue()[player.getDisplayManager().getDisplayMode().ordinal()]);
		}
		open(752, 137, 9);
	}

	/**
	 * Sends a interface tab to the window frame.
	 * 
	 * @param windowId The window id to use.
	 * @param interfaceId The interface id to send.
	 * @param childId The child id of the window.
	 */
	public void open(int windowId, int interfaceId, int childId) {
		player.getPacketProcessor().processInterface(windowId, interfaceId, childId, true);
	}

	/**
	 * Sends a interface tab to the window frame.
	 * 
	 * @param interfaceId The interface id to send.
	 * @param childId The child id of the window.
	 */
	public void open(int interfaceId, int childId) {
		player.getPacketProcessor().processInterface(player.getDisplayManager().getDisplayMode().getWindowId(), interfaceId, childId, true);
	}

}
