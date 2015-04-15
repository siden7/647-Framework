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
package org.ivy.game.world;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 24, 2015
 */
public class WorldContext {

	/**
	 * Represents the actual world to use.
	 */
	private final World world;

	/**
	 * Represents the world id to use.
	 */
	private final int worldId;

	/**
	 * Represents the activity to use.
	 */
	private final String activity;

	/**
	 * Represents the country id to use.
	 */
	private final int countryId;

	/**
	 * Represents if the world is members only.
	 */
	private final boolean members;

	/**
	 * Represents if the world has lootshare.
	 */
	private final boolean lootshare;

	/**
	 * Constructs a new {@code World} {@code Object}.
	 * 
	 * @param world The world to use.
	 * @param worldId The world id to use for each world.
	 * @param activity The activity to use for each world.
	 * @param countryId The country id to use for each world.
	 * @param members If the world is members.
	 * @param lootshare If the world has lootshare.
	 */
	private WorldContext(World world, int worldId, String activity, int countryId, boolean members, boolean lootshare) {
		this.world = world;
		this.worldId = worldId;
		this.activity = activity;
		this.countryId = countryId;
		this.members = members;
		this.lootshare = lootshare;
	}

	/**
	 * Creates a new world.
	 * 
	 * @param worldId The world id.
	 * @param activity The activity.
	 * @param countryId The country id.
	 * @param members If the world is members.
	 * @param lootshare If the world has lootshare.
	 * @return The created world.
	 */
	public static WorldContext createWorld(World world, int worldId, String activity, int countryId, boolean members, boolean lootshare) {
		return new WorldContext(world, worldId, activity, countryId, members, lootshare);
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
	 * Gets the world id.
	 * 
	 * @return the worldId
	 */
	public int getWorldId() {
		return worldId;
	}

	/**
	 * Gets the activity.
	 * 
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * Gets the country id.
	 * 
	 * @return the countyId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * Gets if the world is members only.
	 * 
	 * @return the members
	 */
	public boolean isMembers() {
		return members;
	}

	/**
	 * Gets if the world has lootshare.
	 * 
	 * @return the lootshare
	 */
	public boolean isLootshare() {
		return lootshare;
	}

}
