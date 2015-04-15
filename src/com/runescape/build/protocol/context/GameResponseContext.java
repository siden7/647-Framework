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

import utilities.cryption.isaac.IsaacRandomBlueprint;

import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 22, 2015
 */
public class GameResponseContext {

	/**
	 * Represents the {@code ISAACPair} to use.
	 */
	private final IsaacRandomBlueprint isaacPair;

	/**
	 * Represents the {@code Class} to use.
	 */
	private final Class<?> clazz;

	/**
	 * Represents the {@code IoWriteEvent} to use.
	 */
	private final IoWriteEvent buffer;

	/**
	 * Constructs a new {@code GameResponseContext} {@code Object}.
	 * 
	 * @param clazz The {@code Class} to use.
	 * @param buffer The {@code IoWriteEvent} to use.
	 */
	public GameResponseContext(IsaacRandomBlueprint isaacPair, Class<?> clazz, IoWriteEvent buffer) {
		this.isaacPair = isaacPair;
		this.clazz = clazz;
		this.buffer = buffer;
	}

	/**
	 * Gets the isaac pair.
	 * 
	 * @return the isaacPair
	 */
	public IsaacRandomBlueprint getIsaacPair() {
		return isaacPair;
	}

	/**
	 * Gets the class.
	 * 
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * Gets the buffer.
	 * 
	 * @return the buffer
	 */
	public IoWriteEvent getBuffer() {
		return buffer;
	}

}
