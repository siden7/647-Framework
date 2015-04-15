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
import com.runescape.build.packet.context.impl.WindowPacketContext;
import com.runescape.build.packet.encode.PacketEncoder;
import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
@PacketHeader(packet = PacketType.STANDARD)
public class WindowPacketEncoder implements PacketEncoder<WindowPacketContext> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.build.packet.encode.PacketEncoder#encodePacket(com.runescape.build.packet.context.PacketContext)
	 */
	@Override
	public IoWriteEvent encodePacket(WindowPacketContext context) {
		IoWriteEvent buffer = IoWriteEvent.create(61);
		return buffer.writeLEShort(context.getWindowId()).writeA(context.getWindowType());
	}

}
