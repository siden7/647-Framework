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

import com.runescape.build.protocol.ProtocolResponse;
import com.runescape.build.protocol.decode.LoginDecoder.LoginRequest;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 20, 2015
 */
public class LoginResponseContext {

	/**
	 * Represents the {@code LoginRequest} to use.
	 */
	private final LoginRequest loginRequest;

	/**
	 * Represents the username to use.
	 */
	private final String username;

	/**
	 * Represents the response id to use.
	 */
	private final ProtocolResponse responseCode;

	/**
	 * Constructs a new {@code LoginResponseContext} {@code Object}.
	 * 
	 * @param loginRequest The {@code LoginRequest} to use for this context.
	 * @param username The username to use for this context.
	 * @param responseCode The {@code ProtocolResponse} to use for this context.
	 */
	public LoginResponseContext(LoginRequest loginRequest, String username, ProtocolResponse responseCode) {
		this.loginRequest = loginRequest;
		this.username = username;
		this.responseCode = responseCode;
	}

	/**
	 * Gets the login request.
	 * 
	 * @return the loginRequest
	 */
	public LoginRequest getLoginRequest() {
		return loginRequest;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the response id.
	 * 
	 * @return the responseCode
	 */
	public ProtocolResponse getResponseCode() {
		return responseCode;
	}

}
