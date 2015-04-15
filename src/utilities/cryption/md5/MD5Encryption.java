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
package utilities.cryption.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 13, 2015
 */
public class MD5Encryption {

	/**
	 * Encrypts a buffer using MD5 algorithm
	 * 
	 * @param buffer The buffer to encrypt
	 * @return The encrypted buffer
	 */
	public static final byte[] encryptMD5(byte[] buffer) {
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			MD5.update(buffer);
			byte[] digest = MD5.digest();
			MD5.reset();
			return digest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
