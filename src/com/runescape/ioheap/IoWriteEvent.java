package com.runescape.ioheap;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import utilities.buffer.ByteBufferUtils;
import utilities.cryption.isaac.IsaacRandomBlueprint;

import com.runescape.build.packet.PacketHeader;
import com.runescape.build.packet.PacketHeader.PacketType;

/**
 * Represents an outgoing packet.
 * 
 * @author Emperor
 */
public class IoWriteEvent implements IoHeap {

	/**
	 * The bit masks.
	 */
	private static final int[] BIT_MASK_OUT = new int[] { 0, 0x1, 0x3, 0x7, 0xf, 0x1f, 0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff, 0xfff, 0x1fff, 0x3fff, 0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, -1 };

	/**
	 * The opcode.
	 */
	private int opcode;

	/**
	 * The bit position.
	 */
	private int bitPosition = 0;

	/**
	 * The channel buffer.
	 */
	private final ByteBuf buffer = Unpooled.buffer();

	/**
	 * Constructs a new {@code IoHeapWriter} {@code Object}, <br>
	 * with {@link PacketHeader#STANDARD} as packet type and -1 as opcode.
	 */
	private IoWriteEvent() {
		this(-1);
	}

	/**
	 * Constructs a new {@code IoHeapWriter} {@code Object}, with {@link PacketHeader#STANDARD} as packet type.
	 * 
	 * @param opcode The opcode.
	 */
	private IoWriteEvent(int opcode) {
		this.opcode = opcode;
	}

	/**
	 * Resets the outgoing packet.
	 */
	public void clear() {
		this.bitPosition = 0;
		buffer.clear();
	}

	/**
	 * writes a byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent write(int val) {
		buffer.writeByte(val);
		return this;
	}

	/**
	 * writes a byte-array on the buffer.
	 * 
	 * @param b The byte-array.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeBytes(byte[] b) {
		buffer.writeBytes(b);
		return this;
	}

	/**
	 * writes a different channel buffer on the current buffer.
	 * 
	 * @param other The other channel buffer.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public void writeBytes(ByteBuf other) {
		buffer.writeBytes(other);
	}

	/**
	 * writes a short on the buffer.
	 * 
	 * @param s The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeShort(int s) {
		buffer.writeShort((short) s);
		return this;
	}

	/**
	 * writes an int on the buffer.
	 * 
	 * @param i The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeInt(int i) {
		buffer.writeInt((int) i);
		return this;
	}

	/**
	 * writes a long on the buffer.
	 * 
	 * @param l The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeLong(long l) {
		buffer.writeLong(l);
		return this;
	}

	/**
	 * Writes a large on the buffer.
	 * 
	 * @param l The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeLarge(long l) {
		buffer.writeByte((int) (l >> 32));
		buffer.writeInt((int) (l & 0xffffffff));
		return this;
	}

	/**
	 * writes an RS2-String on the buffer.
	 * 
	 * @param string The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeRS2String(String string) {
		ByteBufferUtils.writeRS2String(string, buffer);
		return this;
	}

	/**
	 * writes an GJ-String on the buffer.
	 * 
	 * @param string The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeGJString(String string) {
		ByteBufferUtils.writeGJString(string, buffer);
		return this;
	}

	/**
	 * writes a GJ-String on the buffer.
	 * 
	 * @param string The value.
	 * @return The IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeGJString2(String string) {
		ByteBufferUtils.writeGJString2(string, buffer);
		return this;
	}

	/**
	 * writes an A-type short on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeShortA(int val) {
		buffer.writeByte((byte) (val >> 8));
		buffer.writeByte((byte) (val + 128));
		return this;
	}

	/**
	 * writes an A-type byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeA(int val) {
		buffer.writeByte((byte) (val + 128));
		return this;
	}

	/**
	 * writes a Little-Endian A-type short on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeLEShortA(int val) {
		buffer.writeByte((byte) (val + 128));
		buffer.writeByte((byte) (val >> 8));
		return this;
	}

	/**
	 * Starts the bit access.
	 * 
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent initBitAccess() {
		bitPosition = buffer.writerIndex() * 8;
		return this;
	}

	/**
	 * Ends the bit access.
	 * 
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent finishBitAccess() {
		buffer.writerIndex((bitPosition + 7) / 8);
		return this;
	}

	/**
	 * writes bits on the buffer.
	 * 
	 * @param numBits The numBits.
	 * @param value The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeBits(int numBits, int value) {
		int bytePos = bitPosition >> 3;
		int bitOffset = 8 - (bitPosition & 7);
		bitPosition += numBits;
		int pos = (bitPosition + 7) / 8;
		buffer.ensureWritable(pos + 1); // pos + 1
		buffer.writerIndex(pos);
		byte b;
		for (; numBits > bitOffset; bitOffset = 8) {
			b = buffer.getByte(bytePos);
			buffer.setByte(bytePos, (byte) (b & ~BIT_MASK_OUT[bitOffset]));
			buffer.setByte(bytePos++, (byte) (b | (value >> (numBits - bitOffset)) & BIT_MASK_OUT[bitOffset]));
			numBits -= bitOffset;
		}
		b = buffer.getByte(bytePos);
		if (numBits == bitOffset) {
			buffer.setByte(bytePos, (byte) (b & ~BIT_MASK_OUT[bitOffset]));
			buffer.setByte(bytePos, (byte) (b | value & BIT_MASK_OUT[bitOffset]));
		} else {
			buffer.setByte(bytePos, (byte) (b & ~(BIT_MASK_OUT[numBits] << (bitOffset - numBits))));
			buffer.setByte(bytePos, (byte) (b | (value & BIT_MASK_OUT[numBits]) << (bitOffset - numBits)));
		}
		return this;
	}

	/**
	 * writes a C-type byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeC(int val) {
		buffer.writeByte((byte) -val);
		return this;
	}

	/**
	 * writes a Little-Endian short on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeLEShort(int val) {
		buffer.writeByte((byte) val);
		buffer.writeByte((byte) (val >> 8));
		return this;
	}

	/**
	 * writes a v1-int on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeIntA(int val) {
		buffer.writeByte((byte) (val >> 8));
		buffer.writeByte((byte) val);
		buffer.writeByte((byte) (val >> 24));
		buffer.writeByte((byte) (val >> 16));
		return this;
	}

	/**
	 * writes a v2-int on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeIntB(int val) {
		buffer.writeByte((byte) (val >> 16));
		buffer.writeByte((byte) (val >> 24));
		buffer.writeByte((byte) val);
		buffer.writeByte((byte) (val >> 8));
		return this;
	}

	/**
	 * writes a Little-Endian int on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeLEInt(int val) {
		buffer.writeByte((byte) val);
		buffer.writeByte((byte) (val >> 8));
		buffer.writeByte((byte) (val >> 16));
		buffer.writeByte((byte) (val >> 24));
		return this;
	}

	/**
	 * writes a byte-array on the buffer.
	 * 
	 * @param data The byte-array.
	 * @param offset The writer index to start from.
	 * @param length The length.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeBytes(byte[] data, int offset, int length) {
		buffer.writeBytes(data, offset, length);
		return this;
	}

	/**
	 * writes an A-type byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeA(byte val) {
		buffer.writeByte((byte) (val + 128));
		return this;
	}

	/**
	 * writes an S-type byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeS(int val) {
		buffer.writeByte((byte) (128 - val));
		return this;
	}

	/**
	 * writes A-type bytes on the array.
	 * 
	 * @param data The byte-array.
	 * @param offset The offset.
	 * @param len The length.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeBytesA(byte[] data, int offset, int len) {
		for (int k = offset; k < len; k++) {
			buffer.writeByte((byte) (data[k] + 128));
		}
		return this;
	}

	/**
	 * writes a byte-array on the buffer using the 'LIFO' manner.
	 * 
	 * @param is The byte-array.
	 * @param offset The index to start from.
	 * @param length The length.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeReverse(byte[] is, int offset, int length) {
		for (int i = (offset + length - 1); i >= offset; i--) {
			write(is[i]);
		}
		return this;
	}

	/**
	 * writes a A-type byte array on the buffer using the 'LIFO' manner.
	 * 
	 * @param is The byte-array.
	 * @param offset The index to start from.
	 * @param length The length.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeReverseA(byte[] is, int offset, int length) {
		for (int i = (offset + length - 1); i >= offset; i--) {
			writeA(is[i]);
		}
		return this;
	}

	/**
	 * writes a tri-byte on the buffer.
	 * 
	 * @param val The value.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent writeMedium(int val) {
		buffer.writeByte((byte) (val >> 16));
		buffer.writeByte((byte) (val >> 8));
		buffer.writeByte((byte) val);
		return this;
	}

	/**
	 * Sets the opcode.
	 * 
	 * @param id The opcode.
	 * @return This IoHeapWriter instance, for chaining.
	 */
	public IoWriteEvent setOpcode(int id) {
		this.opcode = id;
		return this;
	}

	/**
	 * writes a smart.
	 * 
	 * @param val The value.
	 * @return This instance for chaining.
	 */
	public IoWriteEvent writeSmart(int val) {
		if (val >= 128) {
			writeShort(val + 32768);
		} else {
			write(val);
		}
		return this;
	}

	/**
	 * writes a smart.
	 * 
	 * @param val The value.
	 * @return This instance for chaining.
	 */
	public IoWriteEvent writeIntSmart(int val) {
		if (val >= 32768) {
			writeInt(val + 32768);
		} else {
			writeShort(val);
		}
		return this;
	}

	/**
	 * This method should be inherited by sub-classes of this class, and will be used to prepare the packet to send.
	 * 
	 * @return The outgoing packet instance.
	 */
	public IoWriteEvent get() {
		return this;
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
	 * @see com.runescape.ivy.rs2.network.codec.packet.PacketBuffer#getOpcode()
	 */
	public int getPacketId() {
		return opcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.runescape.ivy.rs2.network.codec.packet.PacketBuffer#isRaw()
	 */
	@Override
	public boolean isRaw() {
		return opcode == -1;
	}

	/**
	 * Serializes the channel buffer.
	 * 
	 * @param isaacPair The {@code IsaacRandomBlueprint} to use.
	 * @param clazz The class to use for the packet header.
	 * @param message The message to serialize.
	 * @return The serialized buffer.
	 */
	public static ByteBuf serialize(IsaacRandomBlueprint isaacPair, Class<?> clazz, IoWriteEvent message) {
		if (!message.isRaw()) {
			int packetLength = message.getBuffer().readableBytes() + 3;
			ByteBuf response = Unpooled.buffer(packetLength);
			if (message.getPacketId() > 127) {
				response.writeByte((byte) 128 + isaacPair.getOutput().getNextValue());
			}
			response.writeByte((byte) message.getPacketId() + isaacPair.getOutput().getNextValue());
			if (getPacket(clazz).equals(PacketType.VAR_BYTE)) {
				response.writeByte((byte) message.getBuffer().readableBytes());
			} else if (getPacket(clazz).equals(PacketType.VAR_SHORT)) {
				if (packetLength > 65535) // Stack overflow.
					throw new IllegalStateException("Could not send a packet with " + packetLength + " bytes within 16 bits.");
				response.writeByte((byte) (message.getBuffer().readableBytes() >> 8));
				response.writeByte((byte) message.getBuffer().readableBytes());
			}
			response.writeBytes(message.getBuffer());
			return response;
		}
		return message.getBuffer();
	}

	/**
	 * Gets the specified packet type.
	 * 
	 * @param clazz The class to use.
	 * @return The packet type.
	 */
	private static PacketType getPacket(Class<?> clazz) {
		PacketHeader header = clazz.getAnnotation(PacketHeader.class);
		if (header == null) {
			return PacketType.STANDARD;
		}
		return header.packet();
	}

	/**
	 * @param ioHeader
	 * @return
	 */
	public static IoWriteEvent create(int opcode) {
		return new IoWriteEvent(opcode);
	}

	/**
	 * @return
	 */
	public static IoWriteEvent create() {
		return new IoWriteEvent();
	}

	/**
	 * @param response
	 */
	public void transfer(IoWriteEvent response) {
		buffer.writeBytes(response.getBuffer());
	}

}