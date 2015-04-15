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

import org.ivy.game.node.entity.player.Account.AccountType;

import com.runescape.build.packet.context.PacketContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 27, 2015
 */
public class ChatPacketContext implements PacketContext {

	/**
	 * Represents the message to use.
	 */
	private final String message;

	/**
	 * Represents the {@code AccountType} to use.
	 */
	private final AccountType accountType;

	/**
	 * Represents the index to use.
	 */
	private final int index;

	/**
	 * Represents the chat effect to use.
	 */
	private final int effect;

	/**
	 * Constructs a new {@code ChatPacketContext} {@code Object}.
	 * 
	 * @param message The message to use for this context.
	 * @param accountType The {@code AccountType} to use.
	 * @param index The index to use.
	 * @param effect The effect of the chat.
	 */
	private ChatPacketContext(String message, AccountType accountType, int index, int effect) {
		this.message = message;
		this.accountType = accountType;
		this.index = index;
		this.effect = effect;
	}

	/**
	 * Creates a new {@code ChatPacketContext}.
	 * 
	 * @param message The message to create.
	 * @param accountType The {@code AccountType} to create.
	 * @param index The index to create.
	 * @param effect The effect to create.
	 * @return The created context.
	 */
	public static ChatPacketContext createContext(String message, AccountType accountType, int index, int effect) {
		return new ChatPacketContext(message, accountType, index, effect);
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
	 * Gets the account type.
	 * 
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the effect.
	 * 
	 * @return the effect
	 */
	public int getEffect() {
		return effect;
	}

}
