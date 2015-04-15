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
package org.ivy.game.node.entity.render.flag.appearance;

import io.netty.buffer.ByteBuf;

import org.ivy.game.node.entity.player.Player;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 1, 2015
 */
public abstract class BodyAppearance {

	/**
	 * Represents the style id of each {@code BodyPart}.
	 */
	protected int styleId;

	/**
	 * Represents the color id of each {@code BodyPart}.
	 */
	protected int colorId;

	/**
	 * Constructs a new {@code BodyPart} {@code Object}.
	 * 
	 * @param styleId The style id to use.
	 */
	public BodyAppearance(int styleId) {
		this(styleId, 0);
	}

	/**
	 * Constructs a new {@code BodyPart} {@code Object}.
	 * 
	 * @param styleId The style id to use.
	 * @param colorId The color id to use.
	 */
	public BodyAppearance(int styleId, int colorId) {
		this.styleId = styleId;
		this.colorId = colorId;
	}

	/**
	 * Builds a specific {@code BodyPart}.
	 * 
	 * @param buffer The {@code IoHeapWriter} used for building.
	 * @param player The {@code Player} to build.
	 */
	public abstract void buildBodyPart(ByteBuf buffer, Player player);

	/**
	 * Gets the style id of this {@code BodyPart}.
	 * 
	 * @return styleId
	 */
	public int getStyleId() {
		return styleId;
	}

	/**
	 * Sets the style id of this {@code BodyPart}.
	 * 
	 * @param styleId the styleId to set
	 */
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	/**
	 * Gets the color id of this {@code BodyPart}.
	 * 
	 * @return the colorId
	 */
	public int getColorId() {
		return colorId;
	}

	/**
	 * Sets the color id of this {@code BodyPart}.
	 * 
	 * @param colorId the colorId to set
	 */
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

}
