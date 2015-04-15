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
package utilities.buffer;

import io.netty.buffer.ByteBuf;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 21, 2015
 */
public class ByteBufferUtils {

	/**
	 * Reads a {@code String} from a specific buffer.
	 * 
	 * @param buf The buffer.
	 * @return The decoded {@code String}.
	 */
	public static String readString(ByteBuf buf) {
		StringBuilder bldr = new StringBuilder();
		int b;
		while ((b = buf.readByte()) != 0) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	/**
	 * Gets a string from the byte buffer.
	 * 
	 * @param string The string.
	 * @param buffer The byte buffer.
	 * @return The string.
	 */
	public static ByteBuf writeGJString(String string, ByteBuf buffer) {
		return buffer.writeByte(0).writeBytes(string.getBytes()).writeByte(0);
	}

	/**
	 * Gets a string from the byte buffer.
	 * 
	 * @param string The string.
	 * @param buffer The byte buffer.
	 * @return The string.
	 */
	public static ByteBuf writeRS2String(String string, ByteBuf buffer) {
		return buffer.writeBytes(string.getBytes()).writeByte(0);
	}

	/**
	 * writes a GJ-String on the buffer.
	 * 
	 * @param string The value.
	 * @return The IoHeapWriter instance, for chaining.
	 */
	public static ByteBuf writeGJString2(String string, ByteBuf buffer) {
		byte[] packed = new byte[256];
		int length = packGJString2(0, packed, string);
		buffer.writeByte(0).writeBytes(packed, 0, length).writeByte(0);
		return buffer;
	}

	/**
	 * Converts a String to an Integer?
	 * 
	 * @param position The position.
	 * @param buffer The buffer used.
	 * @param string The String to convert.
	 * @return The Integer.
	 */
	public static int packGJString2(int position, byte[] buffer, String string) {
		int length = string.length();
		int offset = position;
		for (int i = 0; length > i; i++) {
			int character = string.charAt(i);
			if (character > 127) {
				if (character > 2047) {
					buffer[offset++] = (byte) ((character | 919275) >> 12);
					buffer[offset++] = (byte) (128 | ((character >> 6) & 63));
					buffer[offset++] = (byte) (128 | (character & 63));
				} else {
					buffer[offset++] = (byte) ((character | 12309) >> 6);
					buffer[offset++] = (byte) (128 | (character & 63));
				}
			} else
				buffer[offset++] = (byte) character;
		}
		return offset - position;
	}

	/**
	 * Reads a {@code Integer}.
	 * 
	 * @param index The index.
	 * @param buffer The buffer.
	 * @return The decoded {@code Integer}.
	 */
	public static int readInt(int index, byte[] buffer) {
		return ((buffer[index++] & 0xff) << 24) | ((buffer[index++] & 0xff) << 16) | ((buffer[index++] & 0xff) << 8) | (buffer[index++] & 0xff);
	}

	/**
	 * Writes a {@code Integer}.
	 * 
	 * @param value The value of the integer.
	 * @param index The index.
	 * @param buffer The buffer byte array.
	 */
	public static void writeInt(int value, int index, byte[] buffer) {
		buffer[index++] = (byte) (value >> 24);
		buffer[index++] = (byte) (value >> 16);
		buffer[index++] = (byte) (value >> 8);
		buffer[index++] = (byte) value;
	}

}
