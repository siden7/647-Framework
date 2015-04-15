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

import org.ivy.game.node.entity.player.managers.display.DisplayManager.DisplayMode;

import utilities.cryption.isaac.IsaacRandomBlueprint;

import com.runescape.build.protocol.decode.LoginDecoder.LoginRequest;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 20, 2015
 */
public class LoginRequestContext {

	/**
	 * Represents the {@code LoginRequest} to use.
	 */
	private final LoginRequest loginRequest;

	/**
	 * Represents the revision to use
	 */
	private final int major;

	/**
	 * Represents the username to use.
	 */
	private final String username;

	/**
	 * Represents the password to use.
	 */
	private final String password;

	/**
	 * Represents the {@code ISAACPair} to use.
	 */
	private final IsaacRandomBlueprint isaacPair;

	/**
	 * Represents the {@code DisplayMode} to use.
	 */
	private final DisplayMode displayMode;

	/**
	 * Constructs a new {@code LoginRequestContext} {@code Object}.
	 * 
	 * @param loginRequest The {@code LoginRequest} to use.
	 * @param major The revision to use.
	 * @param username The username to use.
	 * @param password The password to use.
	 * @param isaacPair The {@code ISAACPair} to use.
	 * @param displayMode The {@code DisplayMode} to use.
	 */
	public LoginRequestContext(LoginRequest loginRequest, int major, String username, String password, IsaacRandomBlueprint isaacPair, DisplayMode displayMode) {
		this.loginRequest = loginRequest;
		this.major = major;
		this.username = username;
		this.password = password;
		this.isaacPair = isaacPair;
		this.displayMode = displayMode;
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
	 * Gets the revision.
	 * 
	 * @return the revision
	 */
	public int getMajorRevision() {
		return major;
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
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the isaac pair.
	 * 
	 * @return the isaacPair
	 */
	public IsaacRandomBlueprint getIsaacPair() {
		return isaacPair;
	}

	/**
	 * Gets the display mode.
	 * 
	 * @return the displayMode
	 */
	public DisplayMode getDisplayMode() {
		return displayMode;
	}

}
