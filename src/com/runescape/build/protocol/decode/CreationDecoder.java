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
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.math.BigInteger;
import java.util.List;

import utilities.buffer.ByteBufferUtils;
import utilities.cryption.xtea.XTEACryption;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolReactor.Protocol;
import com.runescape.build.protocol.ProtocolRequest;
import com.runescape.build.protocol.context.CreationRequestContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 23, 2015
 */
@ProtocolRequest(request = Protocol.REQUEST_CREATION)
public class CreationDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.isReadable()) {
			/*
			 * This represents what the current buffer size should be.
			 */
			int size = in.readShort();
			if (in.readableBytes() != size) {
				ctx.channel().disconnect();
				return;
			}

			/*
			 * This is the revision of the client requesting the account creation.
			 */
			int revision = in.readShort();

			/*
			 * Create a precondition to check if the revision is 751.
			 */
			if (revision != RuneScape.getGameContext().getRevision()) {
				ctx.channel().disconnect();
				return;
			}

			/*
			 * Builds new data based on the amount of bytes currently in the buffer for rsa.
			 */
			byte[] rsaData = new byte[in.readUnsignedShort()];
			in.readBytes(rsaData);

			/*
			 * Creates a new buffer using the rsa block.
			 */
			final BigInteger PRIVATE_KEY = new BigInteger("7657841432322548474472376777167036368335085594889496533455407838974548219761081283077431685466093593749287585758871380262617745128907047996940891028853141");
			final BigInteger MODULUS = new BigInteger("10200027517633535747240943780769385250321657310169561496515803173442167483252890730495279950237580961760454254994366023652435704445910108971416907763572941");
			ByteBuf block = Unpooled.wrappedBuffer(new BigInteger(rsaData).modPow(PRIVATE_KEY, MODULUS).toByteArray());

			/*
			 * This is the block id. This is always 10.
			 */
			int blockId = block.readUnsignedByte();

			/*
			 * Create a precondition to check if the block id is 10.
			 */
			if (blockId != 10) {
				ctx.channel().disconnect();
				return;
			}

			/*
			 * Creates an array of integers to use for the isaac random.
			 */
			int[] isaacSeed = new int[4];
			for (int i = 0; i < isaacSeed.length; i++) {
				isaacSeed[i] = block.readInt();
			}

			/*
			 * Random 10 integers in the client.
			 */
			for (int i = 0; i < 10; i++) {
				block.readInt();
			}

			/*
			 * Builds new data based on the amount of bytes currently in the buffer for xtea.
			 */
			byte[] xteaData = new byte[in.readableBytes()];
			in.readBytes(xteaData);
			XTEACryption.set(isaacSeed).decrypt(xteaData, 0, xteaData.length);

			/*
			 * Sets the current buffer from the rsa block to the xtea block.
			 */
			block = Unpooled.wrappedBuffer(xteaData);

			/*
			 * The requested email.
			 */
			String email = ByteBufferUtils.readString(block);
			System.out.println(email);

			/*
			 * The language id.
			 */
			block.readShort();

			out.add(new CreationRequestContext(email));
		}
	}

}
