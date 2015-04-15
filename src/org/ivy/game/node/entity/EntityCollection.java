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
package org.ivy.game.node.entity;

import java.util.Vector;

import org.ivy.game.world.impl.GameWorld;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 25, 2015
 */
public class EntityCollection<E extends Entity> extends Vector<E> {

	/**
	 * Represents a serial id.
	 */
	private static final long serialVersionUID = 5167702526919208358L;

	/**
	 * Represents the entities of this collection.
	 */
	private Entity[] entities;

	/**
	 * Constructs a new {@code EntityCollection} {@code Object}.
	 */
	public EntityCollection() {
		this.entities = new Entity[GameWorld.MAX_PLAYER_CAP];
		setSize(1);// We want to start at index 1.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Vector#add(java.lang.Object)
	 */
	@Override
	public synchronized boolean add(E e) {
		int index = size();
		if (index < 1 || index > GameWorld.MAX_PLAYER_CAP) {
			throw new IndexOutOfBoundsException();
		}
		add(index, e);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Vector#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		try {
			entities[index] = element;
			element.setIndex(index);
		} finally {
			super.add(index, element);
		}
	}

	/**
	 * Removes a {@code Entity} from this {@code EntityCollection}.
	 * 
	 * @param e The {@code Entity} to remove.
	 */
	public synchronized void remove(E e) {
		remove(e.getIndex());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Vector#remove(int)
	 */
	@Override
	public synchronized E remove(int index) {
		entities[index] = null;
		return super.remove(index);
	}

}
