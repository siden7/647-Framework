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

import com.runescape.build.packet.PacketHeader;
import com.runescape.build.packet.PacketHeader.PacketType;
import com.runescape.build.packet.context.impl.ConfigPacketContext;
import com.runescape.build.packet.encode.PacketEncoder;
import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 8, 2015
 */
@PacketHeader(packet = PacketType.STANDARD)
public class ConfigPacketEncoder implements PacketEncoder<ConfigPacketContext> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.encode.PacketEncoder#encodePacket(com.runescape.build.packet.context.PacketContext)
	 */
	@Override
	public IoWriteEvent encodePacket(ConfigPacketContext context) {
		IoWriteEvent buffer = null;
		if (context.getValue() <= Byte.MIN_VALUE || context.getValue() >= Byte.MAX_VALUE) {
			buffer = IoWriteEvent.create(49);
			buffer.writeShort(context.getId()).writeIntA(context.getValue());
		} else {
			buffer = IoWriteEvent.create(116);
			buffer.writeS(context.getValue()).writeShort(context.getId());
		}
		return buffer;
	}

}
