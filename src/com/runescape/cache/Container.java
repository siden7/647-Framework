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

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 22, 2015
 */
public enum Container {

	/**
	 * This represents the huffman index.
	 */
	HUFFMAN(10);

	/**
	 * Represents the index id.
	 */
	private final int index;

	/**
	 * Constructs a new {@code Index} {@code Object}.
	 * 
	 * @param index The index to use.
	 */
	private Container(int index) {
		this.index = index;
	}

	/**
	 * Gets and returns the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

}
