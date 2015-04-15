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
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

import org.ivy.connect.state.impl.WorldGameState.GameStage;

import utilities.cryption.isaac.IsaacRandomBlueprint;

import com.runescape.build.protocol.ProtocolReactor.Protocol;
import com.runescape.build.protocol.ProtocolRequest;
import com.runescape.build.protocol.context.GameRequestContext;
import com.runescape.ioheap.IoReadEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 22, 2015
 */
@ProtocolRequest(request = Protocol.REQUEST_GAME)
public class GameDecoder extends ReplayingDecoder<GameStage> {

	/**
	 * Represents the packet length for revision 751.
	 */
	private static final int[] PACKET_SIZES = new int[112];

	/**
	 * Represents the {@code ISAACPair} to use.
	 */
	private final IsaacRandomBlueprint isaacPair;

	/**
	 * Represents the incoming packet id.
	 */
	private int opcode;

	/**
	 * Represents the length of the incoming packet id.
	 */
	private int length;

	/**
	 * Constructs a new {@code GameDecoder} {@code Object}.
	 * 
	 * @param isaacPair The {@code ISAACPair} to use.
	 */
	public GameDecoder(IsaacRandomBlueprint isaacPair) {
		super(GameStage.READ_PACKET);
		this.isaacPair = isaacPair;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.isReadable()) {
			switch (state()) {
			case READ_PACKET:
				opcode = 0xff & in.readUnsignedByte() - isaacPair.getInput().getNextValue();
				if (opcode >= 128) {
					opcode = (opcode - 128 << 8) + (in.readUnsignedByte() - isaacPair.getInput().getNextValue());
				}
				if (opcode > PACKET_SIZES.length || opcode < 0) {
					return;
				}
				checkpoint(GameStage.READ_SIZE);
				break;
			case READ_SIZE:
				length = PACKET_SIZES[opcode];
				if (length < 0) {
					switch (length) {
					case -1:
						if (in.isReadable()) {
							length = in.readByte() & 0xff;
						}
						break;
					case -2:
						if (in.readableBytes() >= 2) {
							length = in.readShort() & 0xffff;
						}
						break;
					default:
						length = in.readableBytes();
						break;
					}
				}
				checkpoint(GameStage.FINALIZE);
				break;
			case FINALIZE:
				if (in.readableBytes() >= length) {
					if (length < 0) {
						return;
					}
					byte[] payload = new byte[length];
					in.readBytes(payload, 0, length);
					out.add(new GameRequestContext(opcode, new IoReadEvent(Unpooled.wrappedBuffer(payload))));
				}
				checkpoint(GameStage.READ_PACKET);
				break;
			}
		}
	}

	static {
		PACKET_SIZES[0] = 0;
		PACKET_SIZES[1] = 1;
		PACKET_SIZES[2] = 2;
		PACKET_SIZES[3] = -1;
		PACKET_SIZES[4] = -1;
		PACKET_SIZES[5] = 4;
		PACKET_SIZES[6] = 16;
		PACKET_SIZES[7] = 7;
		PACKET_SIZES[8] = 3;
		PACKET_SIZES[9] = 3;
		PACKET_SIZES[10] = 3;
		PACKET_SIZES[11] = 3;
		PACKET_SIZES[12] = 2;
		PACKET_SIZES[13] = 4;
		PACKET_SIZES[14] = -1;
		PACKET_SIZES[15] = 11;
		PACKET_SIZES[16] = 8;
		PACKET_SIZES[17] = 7;
		PACKET_SIZES[18] = -1;
		PACKET_SIZES[19] = 8;
		PACKET_SIZES[20] = 3;
		PACKET_SIZES[21] = -1;
		PACKET_SIZES[22] = 3;
		PACKET_SIZES[23] = 2;
		PACKET_SIZES[24] = 4;
		PACKET_SIZES[25] = 8;
		PACKET_SIZES[26] = 3;
		PACKET_SIZES[27] = -1;
		PACKET_SIZES[28] = -1;
		PACKET_SIZES[29] = 2;
		PACKET_SIZES[30] = 3;
		PACKET_SIZES[31] = 2;
		PACKET_SIZES[32] = -1;
		PACKET_SIZES[33] = -1;
		PACKET_SIZES[34] = 8;
		PACKET_SIZES[35] = 4;
		PACKET_SIZES[36] = 7;
		PACKET_SIZES[37] = 16;
		PACKET_SIZES[38] = -1;
		PACKET_SIZES[39] = 18;
		PACKET_SIZES[40] = -1;
		PACKET_SIZES[41] = 7;
		PACKET_SIZES[42] = 0;
		PACKET_SIZES[43] = 6;
		PACKET_SIZES[44] = 7;
		PACKET_SIZES[45] = 4;
		PACKET_SIZES[46] = 15;
		PACKET_SIZES[47] = 15;
		PACKET_SIZES[48] = 7;
		PACKET_SIZES[49] = -1;
		PACKET_SIZES[50] = 2;
		PACKET_SIZES[51] = -1;
		PACKET_SIZES[52] = 8;
		PACKET_SIZES[53] = 2;
		PACKET_SIZES[54] = -1;
		PACKET_SIZES[55] = 8;
		PACKET_SIZES[56] = 8;
		PACKET_SIZES[57] = -1;
		PACKET_SIZES[58] = 6;
		PACKET_SIZES[59] = 4;
		PACKET_SIZES[60] = 4;
		PACKET_SIZES[61] = 1;
		PACKET_SIZES[62] = 7;
		PACKET_SIZES[63] = -1;
		PACKET_SIZES[64] = 3;
		PACKET_SIZES[65] = 7;
		PACKET_SIZES[66] = 11;
		PACKET_SIZES[67] = 7;
		PACKET_SIZES[68] = 0;
		PACKET_SIZES[69] = 6;
		PACKET_SIZES[70] = 3;
		PACKET_SIZES[71] = 3;
		PACKET_SIZES[72] = 3;
		PACKET_SIZES[73] = 7;
		PACKET_SIZES[74] = 12;
		PACKET_SIZES[75] = 3;
		PACKET_SIZES[76] = -1;
		PACKET_SIZES[77] = -1;
		PACKET_SIZES[78] = 8;
		PACKET_SIZES[79] = 3;
		PACKET_SIZES[80] = 8;
		PACKET_SIZES[81] = -1;
		PACKET_SIZES[82] = 3;
		PACKET_SIZES[83] = 8;
		PACKET_SIZES[84] = 5;
		PACKET_SIZES[85] = -1;
		PACKET_SIZES[86] = 3;
		PACKET_SIZES[87] = -1;
		PACKET_SIZES[88] = -1;
		PACKET_SIZES[89] = 2;
		PACKET_SIZES[90] = -1;
	}

}
