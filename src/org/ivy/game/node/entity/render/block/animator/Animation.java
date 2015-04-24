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
package org.ivy.game.node.entity.render.block.animator;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Apr 19, 2015
 */
public class Animation {

	/**
	 * Represents the id of the {@code Animation}.
	 */
	private final int animationId;

	/**
	 * Represents the delay of the {@code Animation}.
	 */
	private final int delay;

	/**
	 * Constructs a new {@code Animation} {@code Object}.
	 * 
	 * @param animationId The animation id to use.
	 */
	public Animation(int animationId) {
		this.animationId = animationId;
		this.delay = 0;
	}

	/**
	 * Constructs a new {@code Animation} {@code Object}.
	 * 
	 * @param animationId The animation id to use.
	 * @param delay The delay of the animation.
	 */
	public Animation(int animationId, int delay) {
		this.animationId = animationId;
		this.delay = delay;
	}

	/**
	 * Gets the animation id.
	 * 
	 * @return the animationId
	 */
	public int getAnimationId() {
		return animationId;
	}

	/**
	 * Gets the animation delay.
	 * 
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

}
