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

import org.ivy.game.node.entity.Entity;
import org.ivy.game.node.entity.render.block.UpdateBlock;

import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Apr 5, 2015
 */
public class Animator extends UpdateBlock {
	
	/**
	 * Represents the {@code Animation} of the {@code Entity}.
	 */
	private Animation animation;

	/**
	 * Represents the {@code Graphic} of the {@code Entity}.
	 */
	private Graphic graphic;

	/**
	 * Constructs a new {@code Animator} {@code Object}.
	 * 
	 * @param entity The {@code Entity} to use.
	 */
	public Animator(Entity entity) {
		super(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.game.node.entity.render.flag.UpdateBlock#buildUpdateBlock()
	 */
	@Override
	public void buildUpdateBlock(IoWriteEvent block) {
		if (animation != null) {
			for (int index = 0; index < 4; index++) {
				block.writeShort(animation.getAnimationId());
			}
			block.writeA(animation.getDelay());
		} else if (graphic != null) {
			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.game.node.entity.render.flag.UpdateBlock#getMask()
	 */
	@Override
	public int getMask() {
		return animation != null ? 0x80 : 0x2000;
	}
	
	/**
	 * Gets the animation.
	 * 
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Sets the animation.
	 * 
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * Gets the graphic.
	 * 
	 * @return the graphic
	 */
	public Graphic getGraphic() {
		return graphic;
	}

	/**
	 * Sets the graphic.
	 * 
	 * @param graphic the graphic to set
	 */
	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

}
