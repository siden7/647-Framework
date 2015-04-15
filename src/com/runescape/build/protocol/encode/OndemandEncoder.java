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
package com.runescape.build.protocol.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.alex.store.MainFile;
import com.runescape.RuneScape;
import com.runescape.build.protocol.context.OndemandResponseContext;
import com.runescape.cache.ContainerArchiveData;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public class OndemandEncoder extends MessageToByteEncoder<OndemandResponseContext> {

	/**
	 * Represents the cache archive data.
	 */
	private byte[] archiveData;

	/**
	 * Constructs a new {@code OndemandEncoder} {@code Object}.
	 */
	public OndemandEncoder() {
		super(OndemandResponseContext.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, OndemandResponseContext msg, ByteBuf out) throws Exception {
		if (msg.getContainer() == 0xff && msg.getArchive() == 0xff) {
			getArchiveRequestData(out);
		} else {
			getArchiveRequestData(msg.getContainer(), msg.getArchive(), msg.isPriority(), out);
		}
	}

	/**
	 * Gets the requested cache container archive data.
	 * 
	 * @param container The requested container.
	 * @param archive The requested archive.
	 * @param priority If the request is priority.
	 * @param out The {@code ByteBuf} to use for writing.
	 * @return The data of the requested archive.
	 */
	private ByteBuf getArchiveRequestData(int container, int archive, boolean priority, ByteBuf out) {
		MainFile cache = null;
		if (container == 0xff) {
			cache = RuneScape.getGameContext().getCache().getStore().getIndex255();
		} else {
			cache = RuneScape.getGameContext().getCache().getStore().getIndexes()[container].getMainFile();
		}
		byte[] archiveId = cache.getArchiveData(archive);
		if (archiveId == null) {
			return null;
		}
		int compression = archiveId[0] & 0xff;
		int length = ((archiveId[1] & 0xff) << 24) + ((archiveId[2] & 0xff) << 16) + ((archiveId[3] & 0xff) << 8) + (archiveId[4] & 0xff);
		int settings = compression;
		if (!priority) {
			settings |= 0x80;
		}
		int realLength = compression != 0 ? length + 4 : length;
		out.writeByte((byte) container);
		out.writeShort(archive);
		out.writeByte((byte) settings);
		out.writeInt(length);
		for (int index = 5; index < realLength + 5; index++) {
			if (out.writerIndex() % 512 == 0) {
				out.writeByte((byte) 255);
			}
			out.writeByte(archiveId[index]);
		}
		return out;
	}

	/**
	 * Gets the requested cache container archive data.
	 * 
	 * @param out The {@code ByteBuf} to use for writing.
	 * @return The data of the requested archive.
	 */
	private ByteBuf getArchiveRequestData(ByteBuf out) {
		if (archiveData == null) {
			archiveData = ContainerArchiveData.getContainerArchiveData(RuneScape.getGameContext().getCache());
		}
		out.writeByte((byte) 255);
		out.writeShort(255);
		out.writeByte((byte) 0);
		out.writeInt(archiveData.length);
		int offset = 8;
		for (int index = 0; index < archiveData.length; index++) {
			if (offset == 512) {
				out.writeByte((byte) 255);
				offset = 1;
			}
			out.writeByte(archiveData[index]);
			offset++;
		}
		return out;
	}

}
