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
package org.ivy.game.node.entity.player.managers.display;

import java.util.HashMap;
import java.util.Map;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 10, 2015
 */
public class ComponentElements {

	/**
	 * Represents the components used for the {@code ComponentElements}.
	 */
	private static final Map<Integer, int[]> COMPONENTS = new HashMap<Integer, int[]>();

	/**
	 * Gets the components.
	 * 
	 * @return the components
	 */
	public static Map<Integer, int[]> getComponents() {
		return COMPONENTS;
	}

	static {
		/*
		 * COMPONENTS.put(INTERFACEID, new int[] { FIXED_CHILD, RESIZED_CHILD });
		 */
		COMPONENTS.put(34, new int[] { 218, 102 });// Notes
		COMPONENTS.put(187, new int[] { 217, 101 });// Music
		COMPONENTS.put(190, new int[] { 206, 90 });// Quests
		COMPONENTS.put(261, new int[] { 215, 99 });// Options
		COMPONENTS.put(271, new int[] { 209, 93 });// Prayers
		COMPONENTS.put(192, new int[] { 210, 94 });// Spellbook
		COMPONENTS.put(320, new int[] { 205, 89 });// Stats
		COMPONENTS.put(387, new int[] { 208, 92 });// Equipment
		COMPONENTS.put(884, new int[] { 203, 87 });// Combat Styles
		COMPONENTS.put(550, new int[] { 212, 96 });// Friends
		COMPONENTS.put(464, new int[] { 216, 100 });// Emotes
		COMPONENTS.put(149, new int[] { 207, 91 });// Inventory
		COMPONENTS.put(747, new int[] { 187, 177 });// Summoning Icon
		COMPONENTS.put(748, new int[] { 182, 174 });// Constitution Icon
		COMPONENTS.put(749, new int[] { 184, 175 });// Prayer Icon
		COMPONENTS.put(750, new int[] { 185, 176 });// Run Energy Icon
		COMPONENTS.put(751, new int[] { 67, 16 });// Chatbox Tabs
		COMPONENTS.put(752, new int[] { 192, 69 });// Chatbox
		COMPONENTS.put(1056, new int[] { 204, 88 });// Task system
		COMPONENTS.put(1109, new int[] { 213, 97 });// Friends Chat
		COMPONENTS.put(1110, new int[] { 214, 98 });// Clan Chat
	}

}
