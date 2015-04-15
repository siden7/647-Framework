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

import java.io.IOException;

import org.ivy.game.world.World;

import com.alex.store.Store;
import com.runescape.cache.Cache;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 22, 2015
 */
public class RuneScapeContext {

	/**
	 * Represents the {@code World} to use.
	 */
	private final World world;

	/**
	 * Represents the game server host of this environment.
	 */
	private final String host;

	/**
	 * Represents the game server port of this environment.
	 */
	private final int port;

	/**
	 * Represents the {@code Cache} of the game server.
	 */
	private final Cache cache;

	/**
	 * Represents the revision of the game server.
	 */
	private final int revision;

	/**
	 * Represents if the game server environment is currently debugging.
	 */
	private final boolean debug;

	/**
	 * Constructs a new {@code RuneScapeContext} {@code Object}.
	 * 
	 * @param host The host to use.
	 * @param port The port to use.
	 * @param cache The {@code Cache} to use.
	 * @param revision The revision to use.
	 * @param debug The debug to use.
	 */
	private RuneScapeContext(World world, String host, int port, Cache cache, int revision, boolean debug) {
		this.world = world;
		this.host = host;
		this.port = port;
		this.cache = cache;
		this.revision = revision;
		this.debug = debug;
	}

	/**
	 * Sets-up the {@code RuneScape} game context.
	 * 
	 * @param host The host to use.
	 * @param port The port to use.
	 * @param revision The revision to use.
	 * @param debug The debug to use.
	 * @return The created context.
	 * @throws IOException If the cache isn't found.
	 */
	public static RuneScapeContext setupGame(World world, String host, int port, int revision, boolean debug) throws IOException {
		return new RuneScapeContext(world, host, port, new Cache(new Store(Cache.CACHE_PATH)), revision, debug);
	}

	/**
	 * Gets the world.
	 * 
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Gets the host.
	 * 
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Gets the port.
	 * 
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Gets the cache.
	 * 
	 * @return the cache
	 */
	public Cache getCache() {
		return cache;
	}

	/**
	 * Gets the revision.
	 * 
	 * @return the revision
	 */
	public int getRevision() {
		return revision;
	}

	/**
	 * Gets if debugging.
	 * 
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

}
