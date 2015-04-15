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
package utilities.tools;

import java.math.BigInteger;
import java.security.SecureRandom;

import utilities.ConsoleHandler;

public class RSAGenerator {

	/**
	 * This method starts and run the java application.
	 * 
	 * @param args Arguments to pass during run-time.
	 */
	public static void main(String[] args) {
		final int bits = 512;
		final SecureRandom random = new SecureRandom();
		BigInteger p, q, phi, modulus, publicKey, privateKey;
		do {
			p = BigInteger.probablePrime(bits / 2, random);
			q = BigInteger.probablePrime(bits / 2, random);
			phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

			modulus = p.multiply(q);
			publicKey = new BigInteger("65537");
			privateKey = publicKey.modInverse(phi);
		} while (modulus.bitLength() != bits || privateKey.bitLength() != bits || !phi.gcd(publicKey).equals(BigInteger.ONE));
		ConsoleHandler.handleMessage(RSAGenerator.class, "modulus: " + modulus);
		ConsoleHandler.handleMessage(RSAGenerator.class, "public key: " + publicKey);
		ConsoleHandler.handleMessage(RSAGenerator.class, "private key: " + privateKey);
	}

}
