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
 * @since Feb 28, 2015
 */
public class InterfacePacketContext implements PacketContext {

	/**
	 * Represents the window id to use.
	 */
	private final int windowId;

	/**
	 * Represents the interface id to use.
	 */
	private final int interfaceId;

	/**
	 * Represents the child id to use.
	 */
	private final int childId;

	/**
	 * Represents if walkable.
	 */
	private final boolean walkable;

	/**
	 * Constructs a new {@code InterfacePacketContext} {@code Object}.
	 * 
	 * @param windowId The window id to use.
	 * @param interfaceId The interface id to use.
	 * @param childId The child id to use.
	 * @param walkable If walkable to use.
	 */
	private InterfacePacketContext(int windowId, int interfaceId, int childId, boolean walkable) {
		this.windowId = windowId;
		this.interfaceId = interfaceId;
		this.childId = childId;
		this.walkable = walkable;
	}

	/**
	 * Creates a new {@code InterfacePacketContext} context.
	 * 
	 * @param windowId The window id to use.
	 * @param interfaceId The interface id to use.
	 * @param childId The child id to use.
	 * @param walkable If walkable.
	 * @return The created context.
	 */
	public static InterfacePacketContext createContext(int windowId, int interfaceId, int childId, boolean walkable) {
		return new InterfacePacketContext(windowId, interfaceId, childId, walkable);
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
	 * Gets the interface id.
	 * 
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Gets the child id.
	 * 
	 * @return the childId
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * Gets if walkable.
	 * 
	 * @return the walkable
	 */
	public boolean isWalkable() {
		return walkable;
	}

}
