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

import com.alex.store.Store;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 22, 2015
 */
public class Cache {

	/**
	 * Represents the directory of the {@code Cache}.
	 */
	public static final String CACHE_PATH = System.getProperty("user.home") + "/Documents/647Cache/";

	/**
	 * Represents the {@link Store} to use.
	 */
	private final Store store;

	/**
	 * Constructs a new {@code Cache} {@code Object}.
	 * 
	 * @param store The {@link Store} to use.
	 */
	public Cache(Store store) {
		this.store = store;
	}

	/**
	 * Gets the store.
	 * 
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

}
