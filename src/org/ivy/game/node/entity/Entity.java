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

import org.ivy.game.node.Node;
import org.ivy.game.node.entity.render.block.UpdateBlockProcessor;
import org.ivy.game.node.entity.render.block.UpdateBlockProcessor.UpdateFlag;
import org.ivy.game.node.entity.render.block.animator.Animation;
import org.ivy.game.node.entity.render.block.animator.Graphic;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class Entity extends Node {

	/**
	 * Represents the {@code UpdateBlockProcessor} to use.
	 */
	protected final UpdateBlockProcessor updateBlockProcessor = new UpdateBlockProcessor(this);

	/**
	 * Animates the {@code Entity}.
	 * 
	 * @param graphic The graphic.
	 */
	public void animate(Graphic graphic) {
		updateBlockProcessor.getAnimator().setGraphic(graphic);
		updateBlockProcessor.flagUpdate(UpdateFlag.GRAPHIC);
	}

	/**
	 * Animates the {@code Entity}.
	 * 
	 * @param animation The animation.
	 */
	public void animate(Animation animation) {
		updateBlockProcessor.getAnimator().setAnimation(animation);
		updateBlockProcessor.flagUpdate(UpdateFlag.ANIMATION);
	}

	/**
	 * Gets the update block processor.
	 * 
	 * @return the flagProcessor
	 */
	public UpdateBlockProcessor getUpdateBlockProcessor() {
		return updateBlockProcessor;
	}

}
