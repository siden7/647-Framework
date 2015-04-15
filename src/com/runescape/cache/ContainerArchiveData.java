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
package com.runescape.cache;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.math.BigInteger;

import com.alex.util.whirlpool.Whirlpool;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 22, 2015
 */
public class ContainerArchiveData {

	/**
	 * Gets and returns a requested container archive data.
	 * 
	 * @return The data.
	 */
	public static byte[] getContainerArchiveData(Cache cache) {
		final BigInteger MODULUS = new BigInteger("8293018560497362601510781561153546030837358529541967275851233402939338737469060293939926532572371495305971568399823784120879326032425809226587459656000399");
		final BigInteger PRIVATE_KEY = new BigInteger("7221102540142858144526210546237823791076257714677844645982063343945195910590948642490852081171144826007034958453951698607646190256536030378848168195022321");
		ByteBuf buffer = Unpooled.buffer((cache.getStore().getIndexes().length * 72) + 6 + 65);
		buffer.writeByte(cache.getStore().getIndexes().length);
		for (int index = 0; index < cache.getStore().getIndexes().length; index++) {
			if (cache.getStore().getIndexes()[index] == null) {
				buffer.writeInt(0);
				buffer.writeInt(0);
				buffer.writeBytes(new byte[64]);
				continue;
			}
			buffer.writeInt(cache.getStore().getIndexes()[index].getCRC());
			buffer.writeInt(cache.getStore().getIndexes()[index].getTable().getRevision());
			buffer.writeBytes(cache.getStore().getIndexes()[index].getWhirlpool());
		}
		byte[] data = new byte[buffer.writerIndex()];
		for (int i = 0; i < data.length; i++) {
			data[i] = buffer.getByte(i);
		}
		ByteBuf footer = Unpooled.buffer(65);
		footer.writeByte(0);
		footer.writeBytes(Whirlpool.getHash(data, 0, data.length));
		byte[] hash = new byte[footer.writerIndex()];
		footer.readBytes(hash);
		buffer.writeBytes(new BigInteger(hash).modPow(PRIVATE_KEY, MODULUS).toByteArray());
		data = new byte[buffer.writerIndex()];
		buffer.readBytes(data);
		return data;
	}

}
