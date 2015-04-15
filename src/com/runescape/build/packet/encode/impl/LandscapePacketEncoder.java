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
package com.runescape.build.packet.encode.impl;

import org.ivy.game.world.landscape.Location;

import com.runescape.build.packet.PacketHeader;
import com.runescape.build.packet.PacketHeader.PacketType;
import com.runescape.build.packet.context.impl.LandscapePacketContext;
import com.runescape.build.packet.encode.PacketEncoder;
import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 27, 2015
 */
@PacketHeader(packet = PacketType.VAR_SHORT)
public class LandscapePacketEncoder implements PacketEncoder<LandscapePacketContext> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.encode.PacketEncoder#encodePacket(com.runescape.build.packet.context.PacketContext)
	 */
	@Override
	public IoWriteEvent encodePacket(LandscapePacketContext context) {
		IoWriteEvent buffer = IoWriteEvent.create(73);
		if (context.isLogin()) {
			context.getPlayer().getFlagProcessor().getPlayerUpdate().enterGameWorld(context.getPlayer(), buffer);
		}// Lol I was sending both X chunks.
		buffer.writeLEShort(context.getLocation().getChunkX()).writeC(context.isLogin() ? 1 : 0).writeC(0).writeLEShortA(context.getLocation().getChunkY());
		int chunkX = context.getLocation().getChunkX();
		int chunkY = context.getLocation().getChunkY();
		int hash = Location.REGION_SIZES[0] >> 4;
		int minRegionX = (chunkX - hash) / 8;
		int minRegionY = (chunkY - hash) / 8;
		for (int xCalc = minRegionX < 0 ? 0 : minRegionX; xCalc <= ((chunkX + hash) / 8); xCalc++) {
			for (int yCalc = minRegionY < 0 ? 0 : minRegionY; yCalc <= ((chunkY + hash) / 8); yCalc++) {
				for (int i = 0; i < 4; i++) {
					buffer.writeInt(0);
				}
			}
		}
		return buffer;
	}

}
