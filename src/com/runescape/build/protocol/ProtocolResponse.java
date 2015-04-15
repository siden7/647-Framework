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
package com.runescape.build.protocol;

/**
 * The {@code ProtocolResponse.java} enumation is used to store default values for different reponses we can send to the client. Each one has a specific id and does different tasks, dependent upon the
 * one used. I tried to use specific naming, so the id for each response is easy to know.
 * 
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 17, 2015
 */
public enum ProtocolResponse {

	/**
	 * Establishes successful client connection between the server and the client.
	 */
	SUCCESSFUL(0),

	/**
	 * Makes the client make a pending connection for 2 seconds.
	 */
	PEND_2000MS(1),

	/**
	 * Establishes a successful login request.
	 */
	SUCCESSFUL_LOGIN(2),

	/**
	 * Displays a client message that the requested login username and/or password was incorrect.
	 */
	INVALID_USERNAME_PASSWORD(3),

	/**
	 * Displays a client message that the requested player account is disabled/banned.
	 */
	DISABLED_ACCOUNT(4),

	/**
	 * Displays a client message that the requested player account is already online.
	 */
	LOGGED_IN(5),

	/**
	 * Tells the requested client that it is currently out-of-date to the connected game server.
	 */
	OUT_OF_DATE(6),

	/**
	 * Displays a client message that the game server is currently full.
	 */
	WORLD_FULL(7),

	/**
	 * Displays a client message that the requested login server is currently offline.
	 */
	LOGINSERVER_OFFLINE(8),

	/**
	 * Displays a client message that the connected channel has too many connected channels of the same host address.
	 */
	LOGIN_LIMIT_EXCEEDED(9),

	/**
	 * The current connected channel has a bad session. Usually close the connection after this is sent.
	 */
	BAD_SESSION(10),

	/**
	 * Displays a client message that the requested login server has regected the login request.
	 */
	LOGINSERVER_REJECTED(11),

	/**
	 * Displays a client message that the requested world for login is members only, whilst the player account doesn't have membership.
	 */
	MEMBERS_REQUIRED(12),

	/**
	 * Displays a client message that the requested login is incomplete.
	 */
	INCOMPLETE_LOGIN(13),

	/**
	 * Displays a client message that the game server is currently undergoing a update.
	 */
	SERVER_UPDATING(14),

	/**
	 * Displays a client message that the connected channel has attempted too many logins.
	 */
	LOGIN_ATTEMPTS_EXCEEDED(16),

	/**
	 * Displays a client message that the requested player account's location is inside a members area. This is only sent if the account is requesting to login into a free-to-play game world.
	 */
	INSIDE_MEMBERS_AREA(17);

	/**
	 * Represents the response id for each {@code ProtocolResponse}.
	 */
	private final int responseId;

	/**
	 * Constructs a new {@code ProtocolResponse} {@code Object}.
	 * 
	 * @param responseId The response id to use.
	 */
	private ProtocolResponse(int responseId) {
		this.responseId = responseId;
	}

	/**
	 * Gets the response id.
	 * 
	 * @return the responseId
	 */
	public int getResponseId() {
		return responseId;
	}

}
