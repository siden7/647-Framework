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

import com.runescape.build.packet.decode.PacketDecoder;
import com.runescape.ioheap.IoReadEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class LinkPacketDecoder implements PacketDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.decode.PacketDecoder#decodePacket(org.ivy.game.node.entity.player.Player, com.runescape.ioheap.IoHeapReader)
	 */
	@Override
	public void decodePacket(Player player, IoReadEvent packet) {
		// packet.readString();// www
		// String extension = packet.readString();
		// switch (extension) {
		// case "account_settings.ws?mod=email":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "account_settings.ws?mod=recoveries":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "account_settings.ws?mod=messages":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "account_settings.ws?mod=uidPassport":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "index.ws":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "en/Customer_Support":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "forums.ws":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// case "rs2007-server":
		// session.write(PacketProcessor.processWebsite("http://google.com/"));
		// break;
		// }
	}

}
