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
package com.runescape.build.protocol.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.runescape.build.protocol.ProtocolReactor.Protocol;
import com.runescape.build.protocol.ProtocolRequest;
import com.runescape.build.protocol.context.OndemandEncryptionContext;
import com.runescape.build.protocol.context.OndemandRequestContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
@ProtocolRequest(request = Protocol.REQUEST_ONDEMAND)
public class OndemandDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 4) {
			return;
		}
		if (in.isReadable()) {
			int opcode = in.readUnsignedByte();
			switch (opcode) {
			case 0:
			case 1:
				int container = in.readUnsignedByte();
				int archive = in.readUnsignedShort();
				out.add(new OndemandRequestContext(container, archive, opcode == 1));
				break;
			case 4:
				int key = in.readUnsignedByte();
				in.readerIndex(in.readerIndex() + 2);
				out.add(new OndemandEncryptionContext(key));
				break;
			default:
				in.readUnsignedByte();
				in.readUnsignedShort();
				break;
			}
		}
	}

}
