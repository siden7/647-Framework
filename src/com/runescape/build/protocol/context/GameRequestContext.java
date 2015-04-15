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
package com.runescape.build.protocol.context;

import com.runescape.ioheap.IoReadEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 22, 2015
 */
public class GameRequestContext {

	/**
	 * Represents the packet id to use.
	 */
	private final int packetId;

	/**
	 * Represents the {@code IncomingPacket} to use for this {@code GameRequestContext}.
	 */
	private final IoReadEvent reader;

	/**
	 * Constructs a new {@code GameRequestContext} {@code Object}.
	 * 
	 * @param packetId The packet id to use.
	 * @param reader The {@code IoHeapReader} to use.
	 */
	public GameRequestContext(int packetId, IoReadEvent reader) {
		this.packetId = packetId;
		this.reader = reader;
	}

	/**
	 * Gets the packet id.
	 * 
	 * @return the packetId
	 */
	public int getPacketId() {
		return packetId;
	}

	/**
	 * Gets the incoming packet.
	 * 
	 * @return the incomingPacket
	 */
	public IoReadEvent getIncomingPacket() {
		return reader;
	}

}
