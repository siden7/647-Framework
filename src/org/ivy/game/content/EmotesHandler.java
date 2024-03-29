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
package org.ivy.game.content;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.render.block.animator.Animation;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Apr 15, 2015
 */
public class EmotesHandler {

	/**
	 * Handles a requested emote.
	 * 
	 * @param player The {@code Player} requesting the emote.
	 * @param buttonId The button id used on the emotes interface.
	 */
	public static void handleEmote(Player player, int buttonId) {
		switch (buttonId) {
		case 2:
			player.animate(new Animation(855));
			break;
		case 3:
			player.animate(new Animation(856));
			break;
		}
		player.getPacketProcessor().processPlayerUpdate();
	}

}
