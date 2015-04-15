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
 * @since Feb 22, 2015
 */
public class PingPacketContext implements PacketContext {

	/**
	 * Represents the ping to use.
	 */
	private final int ping;

	/**
	 * Constructs a new {@code PingPacketContext} {@code Object}.
	 * 
	 * @param ping The ping to use.
	 */
	private PingPacketContext(int ping) {
		this.ping = ping;
	}

	/**
	 * Creates a new {@code PingPacketContext}.
	 * 
	 * @param ping The ping to use for creating.
	 * @return The created context.
	 */
	public static PingPacketContext createContext(int ping) {
		return new PingPacketContext(ping);
	}

	/**
	 * Gets the ping.
	 * 
	 * @return the ping
	 */
	public int getPing() {
		return ping;
	}

}
