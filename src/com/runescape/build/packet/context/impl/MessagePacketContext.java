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
public class MessagePacketContext implements PacketContext {

	/**
	 * Represents the message to use for this context.
	 */
	private final String message;

	/**
	 * Represents the channel to use for this context.
	 */
	private final int channel;

	/**
	 * Constructs a new {@code MessagePacketContext} {@code Object}.
	 * 
	 * @param message The message to use.
	 * @param channel The channel to use.
	 */
	private MessagePacketContext(String message, int channel) {
		this.message = message;
		this.channel = channel;
	}

	/**
	 * Creates a new {@code MessagePacketContext}.
	 * 
	 * @param message The message to use.
	 * @param channel The channel to use.
	 * @return The created context.
	 */
	public static MessagePacketContext createContext(String message, int channel) {
		return new MessagePacketContext(message, channel);
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the channel.
	 * 
	 * @return the channel
	 */
	public int getChannel() {
		return channel;
	}

}
