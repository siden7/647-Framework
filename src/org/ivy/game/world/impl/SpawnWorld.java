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
package org.ivy.game.world.impl;

import org.ivy.game.world.World;
import org.ivy.game.world.WorldCollection;
import org.ivy.game.world.WorldContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 25, 2015
 */
public class SpawnWorld extends World {

	/**
	 * Represents the context to use for this {@code SpawnWorld}.
	 */
	private WorldContext context;

	/**
	 * Constructs a new {@code SpawnWorld} {@code Object}.
	 */
	public SpawnWorld() {
		context = WorldContext.createWorld(this, 2, "Spawn World", 255, false, true);
		WorldCollection.add(context);
	}

	/**
	 * Gets this {@code SpawnWorld} context.
	 * 
	 * @return the context
	 */
	public WorldContext getContext() {
		return context;
	}

}
