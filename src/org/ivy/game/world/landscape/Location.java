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
package org.ivy.game.world.landscape;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class Location extends Coordinate {

	/**
	 * Represents the default starting {@code Location}.
	 */
	public static final Location DEFAULT_LOCATION = Location.createLocation(3222, 3222, 0);

	/**
	 * Represents the possible {@link RegionDispatcher} sizes on the <b>Runescape</b> map.
	 */
	public static final int[] REGION_SIZES = new int[] { 104, 120, 136, 168 };

	/**
	 * Constructs a new {@code Location} {@code Object}.
	 * 
	 * @param x The X-coordinate to use.
	 * @param y The Y-coordinate to use.
	 * @param z The Z-coordinate to use.
	 */
	public Location(int x, int y, int z) {
		super(x, y, z);
	}

	/**
	 * Constructs a new {@code Location} {@code Object}.
	 * 
	 * @param location The location to use.
	 */
	public Location(Location location) {
		super(location.getX(), location.getY(), location.getZ());
	}

	/**
	 * Creates a new {@code Location}.
	 * 
	 * @param x The X-coordinate to use.
	 * @param y The Y-coordinate to use.
	 * @return The created location.
	 */
	public static Location createLocation(int x, int y) {
		return new Location(x, y, 0);
	}

	/**
	 * Creates a new {@code Location}.
	 * 
	 * @param x The X-coordinate to use.
	 * @param y The Y-coordinate to use.
	 * @param z The Z-coordinate to use.
	 * @return The created location.
	 */
	public static Location createLocation(int x, int y, int z) {
		return new Location(x, y, z);
	}

	/**
	 * Creates a new {@code Location}.
	 * 
	 * @param location The {@code Location} to use.
	 * @return The created location.
	 */
	public static Location createLocation(Location location) {
		return new Location(location);
	}

	/**
	 * Gets the tile hash of this {@code Location}.
	 * 
	 * @return The tile hash.
	 */
	public int getTileHash() {
		return y + (x << 14) + (z << 28);
	}

	/**
	 * Returns the hash of the region that this {@link Location} is located on.
	 * 
	 * @return The corresponding region hash of this {@link Location}.
	 */
	public int getRegionHash() {
		return getRegionY() + (getRegionX() << 8) + (z << 16);
	}

	/**
	 * Returns the X coordinate corresponding to the chunk of this {@link Location}.
	 * 
	 * @return The X in the chunk of this {@link Location}.
	 */
	public int getChunkX() {
		return (x >> 3);
	}

	/**
	 * Returns the Y coordinate corresponding to the chunk of this {@link Location}.
	 * 
	 * @return The Y in the chunk of this {@link Location}.
	 */
	public int getChunkY() {
		return (y >> 3);
	}

	/**
	 * Returns the X coordinate in the corresponding region.
	 * 
	 * @return The X in the region of this {@link Location}.
	 * @see {@link #getXInRegion()}, not to be confused with this.
	 */
	public int getRegionX() {
		return (x >> 6);
	}

	/**
	 * Returns the Y coordinate in the corresponding region.
	 * 
	 * @return The Y in the region of this {@link Location}.
	 * @see {@link #getYInRegion()}, not to be confused with this.
	 */
	public int getRegionY() {
		return (y >> 6);
	}

	/**
	 * Gets the ID of the current region.
	 * 
	 * @return The region ID.
	 */
	public int getRegionId() {
		return (getChunkX() >> 3) | ((getChunkY() >> 3) << 8);
	}

}
