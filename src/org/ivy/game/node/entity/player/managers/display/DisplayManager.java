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

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.player.managers.PlayerManager;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 28, 2015
 */
public class DisplayManager extends PlayerManager {

	/**
	 * Represents the {@code DisplayMode} to use.
	 */
	private DisplayMode displayMode;

	/**
	 * Represents the width of the display to use.
	 */
	private int displayWidth;

	/**
	 * Represents the height of the display to use.
	 */
	private int displayHeight;

	/**
	 * Constructs a new {@code DisplayManager} {@code Object}.
	 * 
	 * @param player The {@code Player} to use.
	 */
	public DisplayManager(Player player) {
		super(player);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.game.node.entity.player.managers.PlayerManager#refreshManager()
	 */
	@Override
	public void refreshManager() {
		player.getPacketProcessor().processWindow(displayMode.getWindowId(), 0);
	}

	/**
	 * Gets the display mode.
	 * 
	 * @return the displayMode
	 */
	public DisplayMode getDisplayMode() {
		return displayMode;
	}

	/**
	 * Sets the display mode.
	 * 
	 * @param displayMode the displayMode to set
	 */
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}

	/**
	 * Gets the display width.
	 * 
	 * @return the displayWidth
	 */
	public int getDisplayWidth() {
		return displayWidth;
	}

	/**
	 * Sets the display width.
	 * 
	 * @param displayWidth the displayWidth to set
	 */
	public void setDisplayWidth(int displayWidth) {
		this.displayWidth = displayWidth;
	}

	/**
	 * Gets the display height.
	 * 
	 * @return the displayHeight
	 */
	public int getDisplayHeight() {
		return displayHeight;
	}

	/**
	 * Sets the display height.
	 * 
	 * @param displayHeight the displayHeight to set
	 */
	public void setDisplayHeight(int displayHeight) {
		this.displayHeight = displayHeight;
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Feb 27, 2015
	 */
	public enum DisplayMode {

		/**
		 * Represents the fixed {@code DisplayMode}.
		 */
		FIXED_DISPLAY(548),

		/**
		 * Represents the resizable {@code DisplayMode}.
		 */
		RESIZEABLE_DISPLAY(746);

		/**
		 * Represents the window id used for each {@code DisplayMode}.
		 */
		private final int windowId;

		/**
		 * Constructs a new {@code DisplayMode} {@code Object}.
		 * 
		 * @param windowId The window id to use.
		 */
		private DisplayMode(int windowId) {
			this.windowId = windowId;
		}

		/**
		 * Gets the window id.
		 * 
		 * @return the windowId
		 */
		public int getWindowId() {
			return windowId;
		}

	}

}
