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
 * @since Mar 8, 2015
 */
public class GlobalConfigPacketContext implements PacketContext {

	/**
	 * Represents the id to use of the config.
	 */
	private final int id;

	/**
	 * Represents the value to use of the config.
	 */
	private final int value;

	/**
	 * Constructs a new {@code GlobalConfigPacketContext} {@code Object}.
	 * 
	 * @param id The id of the config to use.
	 * @param value The value of the config to use.
	 */
	private GlobalConfigPacketContext(int id, int value) {
		this.id = id;
		this.value = value;
	}

	/**
	 * Creates a {@code GlobalConfigPacketContext} context.
	 * 
	 * @param id The id to use of the context.
	 * @param value The value to use of the context.
	 * @return The created context.
	 */
	public static GlobalConfigPacketContext createContext(int id, int value) {
		return new GlobalConfigPacketContext(id, value);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

}
