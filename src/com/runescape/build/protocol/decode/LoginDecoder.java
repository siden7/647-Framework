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
import java.util.Optional;

import org.ivy.game.node.entity.player.managers.display.DisplayManager.DisplayMode;

import utilities.ConsoleHandler;
import utilities.buffer.ByteBufferUtils;
import utilities.cryption.isaac.IsaacRandom;
import utilities.cryption.isaac.IsaacRandomBlueprint;
import utilities.cryption.xtea.XTEACryption;

import com.runescape.RuneScape;
import com.runescape.build.protocol.ProtocolReactor.Protocol;
import com.runescape.build.protocol.ProtocolRequest;
import com.runescape.build.protocol.context.LoginRequestContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
@ProtocolRequest(request = Protocol.REQUEST_LOGIN)
public class LoginDecoder extends ByteToMessageDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.isReadable()) {

			/*
			 * Reades the login opcode and determines which login type to use.
			 */
			int opcode = in.readUnsignedByte();
			LoginRequest request = Optional.of(opcode == 19 ? LoginRequest.REQUEST_LOBBY : LoginRequest.REQUEST_WORLD).get();
			if (RuneScape.getGameContext().isDebug()) {
				ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tRequest: " + request);
			}

			/*
			 * Creates a precondition to check if the requested login is valid.
			 */
			if (request == null) {
				return;
			}

			/*
			 * This represents what the current buffer size should be.
			 */
			int size = in.readShort();
			if (in.readableBytes() != size) {
				ctx.channel().disconnect();
				return;
			}

			/*
			 * This is the revision of the client requesting the login.
			 */
			int revision = in.readInt();
			if (RuneScape.getGameContext().isDebug()) {
				ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tRequested revision: " + revision);
			}

			/*
			 * Decode the requested login type.
			 */
			switch (request) {
			case REQUEST_LOBBY:
				decodeLobbyRequest(ctx, in, out, revision);
				break;
			case REQUEST_WORLD:
				decodeWorldRequest(ctx, in, out, revision);
				break;
			}
		}
	}

	/**
	 * Decodes a lobby request.
	 * 
	 * @param ctx The {@code ChannelHandlerContext} to use.
	 * @param in The {@code ByteBuf} to use.
	 * @param out The output for the channel.
	 * @param revision The revision of the client requesting the lobby.
	 */
	private void decodeLobbyRequest(ChannelHandlerContext ctx, ByteBuf in, List<Object> out, int revision) {
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
		 * This is the rsa header. This is always 0.
		 */
		long key = block.readLong();

		/*
		 * Create a precondition to check if the rsa header is 0.
		 */
		if (key != 0) {
			ctx.channel().disconnect();
			return;
		}

		/*
		 * The requested password for the login.
		 */
		String password = ByteBufferUtils.readString(block);
		if (RuneScape.getGameContext().isDebug()) {
			ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tPassword: " + password);
		}

		/*
		 * Reads 2 longs representative of the login seeds.
		 */
		long[] loginSeeds = new long[2];
		for (int i = 0; i < loginSeeds.length; i++) {
			loginSeeds[i] = block.readLong();
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
		 * The requested username for this login.
		 */
		String username = ByteBufferUtils.readString(block);
		System.out.println(username);
		if (RuneScape.getGameContext().isDebug()) {
			ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tUsername: " + username);
		}

		/*
		 * Display settings that don't need to be used for the lobby.
		 */
		block.readUnsignedByte();
		block.readUnsignedByte();

		/*
		 * Random data.
		 */
		for (int i = 0; i < 24; i++) {
			block.readByte();
		}

		/*
		 * The requested login token from the client.
		 */
		String loginToken = ByteBufferUtils.readString(block);
		final String LOGIN_TOKEN = "wwGlrZHF5gJcZl7tf7KSRh0MZLhiU0gI0xDX6DwZ-Qk";

		/*
		 * Checks if the login token requested matches what we have set for the server.
		 */
		if (!loginToken.equals(LOGIN_TOKEN)) {
			ctx.channel().disconnect();
			return;
		}

		/*
		 * Set issac cipher information.
		 */
		IsaacRandom input = new IsaacRandom(isaacSeed);
		for (int i = 0; i < 4; i++) {
			isaacSeed[i] += 50;
		}
		IsaacRandom output = new IsaacRandom(isaacSeed);

		/*
		 * Create an isaac pair.
		 */
		IsaacRandomBlueprint pair = new IsaacRandomBlueprint(input, output);

		out.add(new LoginRequestContext(LoginRequest.REQUEST_LOBBY, revision, username, password, pair, null));
	}

	/**
	 * Decodes a world request.
	 * 
	 * @param ctx The {@code ChannelHandlerContext} to use.
	 * @param in The {@code ByteBuf} to use.
	 * @param out The output for the channel.
	 * @param revision The revision of the client requesting the world.
	 */
	private void decodeWorldRequest(ChannelHandlerContext ctx, ByteBuf in, List<Object> out, int revision) {
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
		 * This is the rsa header. This is always 0.
		 */
		long key = block.readLong();

		/*
		 * Create a precondition to check if the rsa header is 0.
		 */
		if (key != 0) {
			ctx.channel().disconnect();
			return;
		}

		/*
		 * The requested password for the login.
		 */
		String password = ByteBufferUtils.readString(block);
		if (RuneScape.getGameContext().isDebug()) {
			ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tPassword: " + password);
		}

		/*
		 * Reads 2 longs representative of the login seeds.
		 */
		long[] loginSeeds = new long[2];
		for (int i = 0; i < loginSeeds.length; i++) {
			loginSeeds[i] = block.readLong();
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
		 * The requested username for this login.
		 */
		String username = ByteBufferUtils.readString(block);
		if (RuneScape.getGameContext().isDebug()) {
			ConsoleHandler.handleMessage(LoginDecoder.class, "LoginDecoder.java\tUsername: " + username);
		}

		/*
		 * Display settings.
		 */
		block.readUnsignedByte();
		int displayId = block.readUnsignedByte();
		block.readUnsignedShort();
		block.readUnsignedShort();
		block.readUnsignedByte();

		/*
		 * Random data.
		 */
		for (int i = 0; i < 24; i++) {
			block.readByte();
		}

		/*
		 * The requested login token from the client.
		 */
		String loginToken = ByteBufferUtils.readString(block);
		final String LOGIN_TOKEN = "wwGlrZHF5gJcZl7tf7KSRh0MZLhiU0gI0xDX6DwZ-Qk";

		/*
		 * Checks if the login token requested matches what we have set for the server.
		 */
		if (!loginToken.equals(LOGIN_TOKEN)) {
			ctx.channel().disconnect();
			return;
		}

		/*
		 * Additional info.
		 */
		block.readInt();
		block.skipBytes(block.readUnsignedByte());
		block.readInt();
		block.readLong();
		boolean hasAditionalInformation = block.readUnsignedByte() == 1;
		if (hasAditionalInformation) {
			ByteBufferUtils.readString(block);
		}
		block.readUnsignedByte();
		block.readUnsignedByte();
		block.readUnsignedByte();
		block.readByte();
		block.readInt();
		ByteBufferUtils.readString(block);
		block.readUnsignedByte();

		/*
		 * Set issac cipher information.
		 */
		IsaacRandom input = new IsaacRandom(isaacSeed);
		for (int i = 0; i < 4; i++) {
			isaacSeed[i] += 50;
		}
		IsaacRandom output = new IsaacRandom(isaacSeed);

		/*
		 * Create an isaac pair.
		 */
		IsaacRandomBlueprint pair = new IsaacRandomBlueprint(input, output);

		out.add(new LoginRequestContext(LoginRequest.REQUEST_WORLD, revision, username, password, pair, Optional.of(displayId == 1 ? DisplayMode.FIXED_DISPLAY : DisplayMode.RESIZEABLE_DISPLAY).get()));
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Feb 20, 2015
	 */
	public enum LoginRequest {
		REQUEST_LOBBY, REQUEST_WORLD;
	}

}
