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
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.StringTokenizer;

import org.joda.time.DateTime;
import org.joda.time.Days;

import utilities.buffer.ByteBufferUtils;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolResponse;
import com.runescape.build.protocol.context.LoginResponseContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public class LoginEncoder extends MessageToByteEncoder<LoginResponseContext> {

	/**
	 * Constructs a new {@code LoginEncoder} {@code Object}.
	 */
	public LoginEncoder() {
		super(LoginResponseContext.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.MessageToByteEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, io.netty.buffer.ByteBuf)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, LoginResponseContext msg, ByteBuf out) throws Exception {
		ByteBuf buffer = Unpooled.buffer(1000);
		out.writeByte(msg.getResponseCode().getResponseId());
		if (msg.getResponseCode().equals(ProtocolResponse.SUCCESSFUL_LOGIN)) {
			switch (msg.getLoginRequest()) {
			case REQUEST_LOBBY:
				buffer.writeByte(2);
				buffer.writeByte(0);
				buffer.writeByte(0);
				buffer.writeByte(0);
				buffer.writeByte(0);
				buffer.writeInt(0);
				buffer.writeInt(0);
				buffer.writeByte(0x2);
				buffer.writeInt(0);
				buffer.writeByte(0x2);
				buffer.writeInt(0);
				buffer.writeShort(getRecoveryQuestionsSetDays());
				buffer.writeShort(0);
				buffer.writeShort(getLastLoggedInDays());
				buffer.writeInt(refreshIPAddress(ctx.channel().remoteAddress().toString().split(":")[0].replace("/", "")));
				buffer.writeByte(3);
				buffer.writeShort(53791);
				buffer.writeShort(53791);
				buffer.writeByte(0);
				ByteBufferUtils.writeGJString(msg.getUsername(), buffer);
				buffer.writeByte(10);
				buffer.writeInt(4650553);
				buffer.writeByte(1);
				buffer.writeShort(1);
				ByteBufferUtils.writeGJString(RuneScape.getGameContext().getHost(), buffer);
				out.writeByte(buffer.writerIndex());
				out.writeBytes(buffer);
				break;
			case REQUEST_WORLD:
				buffer.writeByte(2);
				buffer.writeByte(0);
				buffer.writeByte(0);
				buffer.writeByte(0);
				buffer.writeByte(1);
				buffer.writeByte(0);
				buffer.writeShort(1);
				buffer.writeByte(1);
				buffer.writeMedium(0);
				buffer.writeByte(1);
				ByteBufferUtils.writeGJString(msg.getUsername(), buffer);
				out.writeByte(buffer.writerIndex());
				out.writeBytes(buffer);
				break;
			}
		}
	}

	/**
	 * Refreshes the login days from a starting point to the current date.
	 * 
	 * @return The number of days.
	 */
	private int getLastLoggedInDays() {
		DateTime from = new DateTime(2002, 2, 26, 23, 0);
		DateTime to = new DateTime();
		return Days.daysBetween(from, to).getDays();
	}

	/**
	 * Gets the recovery questions set date.
	 * 
	 * @return The number of days.
	 */
	private int getRecoveryQuestionsSetDays() {
		DateTime from = new DateTime(2002, 2, 26, 23, 0);
		DateTime to = new DateTime();
		return Days.daysBetween(from, to).getDays();
	}

	/**
	 * Refreshes an ip address and converts it into an integer.
	 * 
	 * @param address The ip address to use.
	 * @return The converted ip address.
	 */
	private int refreshIPAddress(String address) {
		StringTokenizer string = new StringTokenizer(address, ".");
		int[] ip = new int[4];
		int index = 0;
		while (string.hasMoreTokens()) {
			ip[index++] = Integer.parseInt(string.nextToken());
		}
		return ((ip[0] << 24) | (ip[1] << 16) | (ip[2] << 8) | (ip[3]));
	}

}
