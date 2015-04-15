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

import org.ivy.game.node.entity.player.Player;

import utilities.StringUtilities;
import utilities.cryption.huffman.Huffman;

import com.runescape.build.packet.decode.PacketDecoder;
import com.runescape.ioheap.IoReadEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 21, 2015
 */
public class ChatPacketDecoder implements PacketDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.decode.PacketDecoder#decodePacket(org.ivy.game.node.entity.player.Player, com.runescape.ioheap.IoHeapReader)
	 */
	@Override
	public void decodePacket(Player player, IoReadEvent packet) {
		packet.readByte();
		packet.readByte();
		int length = packet.readSmart();
		String message = StringUtilities.fixChatMessage(Huffman.decompressHuffman(packet, length));
		if (message.isEmpty()) {
			return;
		}
		if (message.startsWith("::") || message.startsWith(";;") || message.startsWith(";")) {
			/* TODO Commands */
			return;
		}
		player.getPacketProcessor().processChatMessage(message, player.getAccount().getAccountType(), player.getIndex(), 0);
	}

}
