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
package com.runescape;

import org.ivy.connect.GameConnector;
import org.ivy.game.world.impl.GameWorld;
import org.ivy.game.world.impl.SpawnWorld;

import utilities.ConsoleHandler;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 16, 2015
 */
public class RuneScape {

	/**
	 * Represents the {@code RuneScapeContext} to use for this {@code RuneScape}.
	 */
	private static RuneScapeContext gameContext;

	/**
	 * Represents the {@code RuneScapeContext} to use for this {@code RuneScape}.
	 */
	private static RuneScapeContext spawnContext;

	/**
	 * This method starts and run the java application.
	 * 
	 * @param args Arguments to pass during run-time.
	 */
	public static void main(String[] args) {
		try {
			ConsoleHandler.handleMessage(RuneScape.class, "Starting the official Ivy RSPS framework!");
			gameContext = RuneScapeContext.setupGame(new GameWorld(), "127.0.0.1", 43594, 647, false);
			spawnContext = RuneScapeContext.setupGame(new SpawnWorld(), "127.0.0.1", 43594, 647, false);
			GameConnector.connectGame();
			ConsoleHandler.handleMessage(RuneScape.class, "Port is binded to 43594.");
		} catch (Exception cause) {
			ConsoleHandler.handleException(RuneScape.class, cause);
		} finally {
			System.runFinalization();
			ConsoleHandler.handleMessage(RuneScape.class, "Ivy is now online!");
		}
	}

	/**
	 * Gets and returns the server environment.
	 * 
	 * @return the context
	 */
	public static RuneScapeContext getGameContext() {
		return gameContext;
	}

	/**
	 * Gets and returns the spawn server environment.
	 * 
	 * @return the spawnContext
	 */
	public static RuneScapeContext getSpawnContext() {
		return spawnContext;
	}

}
