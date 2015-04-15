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
package org.ivy.game.node.entity.render.flag.appearance.impl;

import io.netty.buffer.ByteBuf;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.render.flag.appearance.BodyAppearance;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 13, 2015
 */
public class Chest extends BodyAppearance {

	/**
	 * Constructs a new {@code Chest} {@code Object}.
	 * 
	 * @param styleId The style id to use for this {@code Chest}.
	 */
	public Chest(int styleId) {
		super(styleId, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.game.node.entity.render.flag.appearance.BodyAppearance#buildBodyPart(io.netty.buffer.ByteBuf, org.ivy.game.node.entity.player.Player)
	 */
	@Override
	public void buildBodyPart(ByteBuf buffer, Player player) {
		buffer.writeShort(0x100 + styleId);
	}

}
