package com.runescape.ioheap;

import io.netty.buffer.ByteBuf;

/**
 * The interface implemented by the incoming and outgoing packets.
 * 
 * @author Emperor
 *
 */
public interface IoHeap {

	/**
	 * If the packet is raw.
	 * 
	 * @return {@code True} if the opcode is {@code -1}.
	 */
	public boolean isRaw();

	/**
	 * Gets the channel buffer.
	 * 
	 * @return The channel buffer.
	 */
	public ByteBuf getBuffer();

}
