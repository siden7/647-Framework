package com.runescape.ioheap;

import io.netty.buffer.ByteBuf;
import utilities.buffer.ByteBufferUtils;

/**
 * Represents a received incoming packet.
 * 
 * @author Emperor
 */
public class IoReadEvent implements IoHeap {

	/**
	 * Represents the packet id.
	 */
	private final int packetId;

	/**
	 * The buffer.
	 */
	private final ByteBuf buffer;

	/**
	 * Constructs a new {@code IoReadEvent} {@code Object}.
	 * 
	 * @param buffer The channel buffer.
	 */
	public IoReadEvent(int packetId, ByteBuf buffer) {
		this.packetId = packetId;
		this.buffer = buffer;
	}

	/**
	 * @return the packetId
	 */
	public int getPacketId() {
		return packetId;
	}

	/**
	 * Reads a byte from the channel buffer.
	 * 
	 * @return The byte.
	 */
	public byte readByte() {
		return buffer.readByte();
	}

	/**
	 * Reads an unsigned byte.
	 * 
	 * @return The byte.
	 */
	public int readUnsignedByte() {
		return buffer.readUnsignedByte();
	}

	/**
	 * Reads bytes from the buffer and stores them on the array.
	 * 
	 * @param b The byte array.
	 */
	public void read(byte[] b) {
		buffer.readBytes(b);
	}

	/**
	 * Reads a short.
	 * 
	 * @return A short.
	 */
	public short readShort() {
		if (buffer.readableBytes() < 2)
			return 0;
		return buffer.readShort();
	}

	/**
	 * Reads an unsigned short.
	 * 
	 * @return An unsigned short.
	 */
	public int readUnsignedShort() {
		return buffer.readUnsignedShort();
	}

	/**
	 * Reads an integer.
	 * 
	 * @return An integer.
	 */
	public int readInt() {
		if (buffer.readableBytes() < 4)
			return 0;
		return buffer.readInt();
	}

	/**
	 * Reads a long.
	 * 
	 * @return A long.
	 */
	public long readLong() {
		if (buffer.readableBytes() < 8)
			return 0;
		return buffer.readLong();
	}

	/**
	 * Reads a type C byte.
	 * 
	 * @return A type C byte.
	 */
	public int readByteC() {
		return -readByte();
	}

	/**
	 * reads a type S byte.
	 * 
	 * @return A type S byte.
	 */
	public int readByteS() {
		return 128 - readByte();
	}

	/**
	 * Reads a little-endian type A short.
	 * 
	 * @return A little-endian type A short.
	 */
	public int readLEShortA() {
		return (buffer.readByte() - 128 & 0xFF) | ((buffer.readByte() & 0xFF) << 8);
	}

	/**
	 * Reads a little-endian short.
	 * 
	 * @return A little-endian short.
	 */
	public int readLEShort() {
		return (buffer.readByte() & 0xFF) | ((buffer.readByte() & 0xFF) << 8);
	}

	/**
	 * Reads a type A short.
	 * 
	 * @return A type A short.
	 */
	public int readShortA() {
		return ((buffer.readByte() & 0xFF) << 8) | (buffer.readByte() - 128 & 0xFF);
	}

	/**
	 * Reads a V1 integer.
	 * 
	 * @return A V1 integer.
	 */
	public int readInt1() {
		int b1 = buffer.readByte() & 0xFF;
		int b2 = buffer.readByte() & 0xFF;
		int b3 = buffer.readByte() & 0xFF;
		int b4 = buffer.readByte() & 0xFF;
		return (b3 << 24 | b4 << 16 | b1 << 8 | b2);
	}

	/**
	 * Reads a V2 integer.
	 * 
	 * @return A V2 integer.
	 */
	public int readInt2() {
		int b1 = buffer.readByte() & 0xFF;
		int b2 = buffer.readByte() & 0xFF;
		int b3 = buffer.readByte() & 0xFF;
		int b4 = buffer.readByte() & 0xFF;
		return (b2 << 24 | b1 << 16 | b4 << 8 | b3);
	}

	/**
	 * reads a 3-byte integer.
	 * 
	 * @return The 3-byte integer.
	 */
	public int readTriByte() {
		return ((buffer.readByte() << 16) & 0xFF) | ((buffer.readByte() << 8) & 0xFF) | (buffer.readByte() & 0xFF);
	}

	/**
	 * Reads a type A byte.
	 * 
	 * @return A type A byte.
	 */
	public int readByteA() {
		return readByte() - 128;
	}

	/**
	 * Reads a RuneScape string.
	 * 
	 * @return The string.
	 */
	public String readRS2String() {
		return ByteBufferUtils.readString(buffer);
	}

	/**
	 * Reads a series of bytes in reverse.
	 * 
	 * @param is The tarread byte array.
	 * @param offset The offset.
	 * @param length The length.
	 */
	public void readReverse(byte[] is, int offset, int length) {
		for (int i = (offset + length - 1); i >= offset; i--) {
			is[i] = buffer.readByte();
		}
	}

	/**
	 * Reads a series of type A bytes in reverse.
	 * 
	 * @param is The tarread byte array.
	 * @param offset The offset.
	 * @param length The length.
	 */
	public void readReverseA(byte[] is, int offset, int length) {
		for (int i = (offset + length - 1); i >= offset; i--) {
			is[i] = (byte) readByteA();
		}
	}

	/**
	 * Reads a series of bytes.
	 * 
	 * @param is The tarread byte array.
	 * @param offset The offset.
	 * @param length The length.
	 */
	public void read(byte[] is, int offset, int length) {
		for (int i = 0; i < length; i++) {
			is[offset + i] = buffer.readByte();
		}
	}

	/**
	 * reads a smart.
	 * 
	 * @return The smart.
	 */
	public int readSmart() {
		int peek = buffer.getByte(buffer.readerIndex());
		if (peek < 128) {
			return (readByte() & 0xFF);
		} else {
			return (readShort() & 0xFFFF) - 32768;
		}
	}

	/**
	 * Reads bytes and stores them on the byte-array.
	 * 
	 * @param textBuffer The byte-array.
	 * @param length The length.
	 */
	public void readBytes(byte[] textBuffer, int length) {
		buffer.readBytes(textBuffer, 0, length);
	}

	/**
	 * Reads a jag string.
	 * 
	 * @return The string.
	 */
	public String readJagString() {
		readByte();
		return readRS2String();
	}

	/**
	 * Reads a low-endian int.
	 * 
	 * @return The int.
	 */
	public int readLEInt() {
		return readUnsignedByte() + (readUnsignedByte() << 8) + (readUnsignedByte() << 16) + (readUnsignedByte() << 24);
	}

	/**
	 * Gets the remaining readable bytes.
	 * 
	 * @return The remaining bytes.
	 */
	public int remaining() {
		return buffer.readableBytes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.network.codec.packet.PacketBuffer#getBuffer()
	 */
	@Override
	public ByteBuf getBuffer() {
		return buffer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.network.codec.packet.PacketBuffer#isRaw()
	 */
	@Override
	public boolean isRaw() {
		return false;
	}

}
