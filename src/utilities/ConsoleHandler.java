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
package utilities;

import org.slf4j.LoggerFactory;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 16, 2015
 */
public class ConsoleHandler {

	/**
	 * Handles a message that is requesting to be send to the jvm console.
	 * 
	 * @param clazz The class where the message is handled from.
	 * @param message The message to print to the jvm console.
	 */
	public static void handleMessage(Class<?> clazz, String message) {
		LoggerFactory.getLogger(clazz).info(message);
	}

	/**
	 * Handles an exception caught by the {@code GameServer}.
	 * 
	 * @param clazz The class where the exception occured.
	 * @param cause The actual pritned {@code Exception} by the jvm.
	 */
	public static void handleException(Class<?> clazz, Throwable cause) {
		LoggerFactory.getLogger(clazz).error(cause.getMessage());
		cause.printStackTrace();
	}

}
