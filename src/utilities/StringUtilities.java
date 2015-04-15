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

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 27, 2015
 */
public class StringUtilities {

	/**
	 * Generates a 'fixed' copy of a chat message.
	 * 
	 * @param message The message to generate.
	 * @return The message.
	 */
	public static String fixChatMessage(String message) {
		StringBuilder text = new StringBuilder();
		boolean wasSpace = true;
		for (int i = 0; i < message.length(); i++) {
			if (wasSpace) {
				text.append(("" + message.charAt(i)).toUpperCase());
				if (!String.valueOf(message.charAt(i)).equals(" ")) {
					wasSpace = false;
				}
			} else {
				text.append(("" + message.charAt(i)).toLowerCase());
			}
			if (String.valueOf(message.charAt(i)).contains(".") || String.valueOf(message.charAt(i)).contains("!") || String.valueOf(message.charAt(i)).contains("?")) {
				wasSpace = true;
			}
		}
		return text.toString();
	}

}
