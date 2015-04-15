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
package com.runescape.build.protocol.context;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 23, 2015
 */
public class OndemandEncryptionContext {

	/**
	 * Represents the encrypted key to use.
	 */
	private int encryptedKey;

	/**
	 * Constructs a new {@code UpdateEncryptionMessage} {@link Object}.
	 * 
	 * @param key The encrypted key.
	 */
	public OndemandEncryptionContext(int encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	/**
	 * Gets the encrypted key to use.
	 * 
	 * @return The used encryption key.
	 */
	public int getEncryptedKey() {
		return encryptedKey;
	}

}
