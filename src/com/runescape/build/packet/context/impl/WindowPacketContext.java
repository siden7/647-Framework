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

import com.runescape.build.packet.context.PacketContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class WindowPacketContext implements PacketContext {

	/**
	 * Represents the window id to use.
	 */
	private final int windowId;

	/**
	 * Represents the window type to use.
	 */
	private final int windowType;

	/**
	 * Constructs a new {@code WindowPacketContext} {@code Object}.
	 * 
	 * @param windowId The window id to use.
	 * @param windowType The window type to use.
	 */
	private WindowPacketContext(int windowId, int windowType) {
		this.windowId = windowId;
		this.windowType = windowType;
	}

	/**
	 * Creates a new {@code WindowPacketContext}.
	 * 
	 * @param windowId The window id to use.
	 * @param windowType The window type to use.
	 * @return The created context.
	 */
	public static WindowPacketContext createContext(int windowId, int windowType) {
		return new WindowPacketContext(windowId, windowType);
	}

	/**
	 * Gets the window id.
	 * 
	 * @return the windowId
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * Gets the window type.
	 * 
	 * @return the windowType
	 */
	public int getWindowType() {
		return windowType;
	}

}
