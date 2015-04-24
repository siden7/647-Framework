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
package org.ivy.game.node.entity.render.block;

import org.ivy.game.node.entity.Entity;

import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 1, 2015
 */
public abstract class UpdateBlock {

	/**
	 * Represents the {@code Entity} to use for this {@code UpdateBlock}.
	 */
	protected final Entity entity;

	/**
	 * Constructs a new {@code UpdateBlock} {@code Object}.
	 * 
	 * @param entity The entity to construct.
	 */
	public UpdateBlock(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Builds a requested update block.
	 * 
	 * @param block The block to use.
	 */
	public abstract void buildUpdateBlock(IoWriteEvent block);

	/**
	 * Gets the mask for each {@code UpdateBlock}.
	 * 
	 * @return The mask id.
	 */
	public abstract int getMask();

}
