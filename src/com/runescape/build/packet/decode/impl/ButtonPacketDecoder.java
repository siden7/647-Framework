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
package com.runescape.build.packet.decode.impl;

import org.ivy.game.content.EmotesHandler;
import org.ivy.game.node.entity.player.Player;

import com.runescape.build.packet.decode.PacketDecoder;
import com.runescape.ioheap.IoReadEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Apr 14, 2015
 */
public class ButtonPacketDecoder implements PacketDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.decode.PacketDecoder#decodePacket(org.ivy.game.node.entity.player.Player, com.runescape.ioheap.IoReadEvent)
	 */
	@Override
	public void decodePacket(Player player, IoReadEvent packet) {
		int hash = packet.readInt();
		int slotId = packet.readLEShortA();
		int itemId = packet.readLEShort();
		int interfaceId = hash >> 16;
		int buttonId = hash & 0xff;
		if (slotId == 65535) {
			slotId = 0;
		}
		if (itemId == 65535) {
			itemId = 0;
		}
		player.getPacketProcessor().processGameMessage("InterfaceId: " + interfaceId + ", ButtonId: " + buttonId + ", " + "SlotId: " + slotId + ", ItemId: " + itemId);
		switch (packet.getPacketId()) {
		case 16:
			switch (interfaceId) {
			case 464:
				EmotesHandler.handleEmote(player, buttonId);
				break;
			}
			break;
		case 52:
			break;
		}
	}

}
