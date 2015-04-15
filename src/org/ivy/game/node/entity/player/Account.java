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
package org.ivy.game.node.entity.player;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class Account {

	/**
	 * Represents the username to use.
	 */
	private final String username;

	/**
	 * Represents the password to use.
	 */
	private final String password;

	/**
	 * Represents the {@code AccountType} to use.
	 */
	private final AccountType accountType;

	/**
	 * Constructs a new {@code Account} {@code Object}.
	 * 
	 * @param username The username of the account.
	 * @param password The password of the account.
	 */
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		this.accountType = AccountType.ADMINISTRATOR_ACCOUNT;
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
	 * Gets the account type.
	 * 
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Feb 25, 2015
	 */
	public enum AccountType {
		NORMAL_ACCOUNT, MODERATOR_ACCOUNT, ADMINISTRATOR_ACCOUNT;
	}

}
