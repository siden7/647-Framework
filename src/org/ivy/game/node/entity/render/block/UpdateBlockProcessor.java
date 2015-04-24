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
package org.ivy.game.node.entity.render.block;

import java.util.BitSet;

import org.ivy.game.node.entity.Entity;
import org.ivy.game.node.entity.npc.NPC;
import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.render.NPCRenderUpdate;
import org.ivy.game.node.entity.render.PlayerRenderUpdate;
import org.ivy.game.node.entity.render.block.animator.Animator;
import org.ivy.game.node.entity.render.block.appearance.Appearance;

import com.runescape.ioheap.IoWriteEvent;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 1, 2015
 */
public class UpdateBlockProcessor {

	/**
	 * Represents the {@code Player} to use for this {@code UpdateBlockProcessor}.
	 */
	private final Entity entity;

	/**
	 * Represents a set of update block flags.
	 */
	private final BitSet flags = new BitSet();

	/**
	 * Represents the {@code PlayerRenderUpdate} to use for this {@code UpdateBlockProcessor}.
	 */
	private final PlayerRenderUpdate playerUpdate = new PlayerRenderUpdate();

	/**
	 * Represents the {@code NPCRenderUpdate} to use for this {@code UpdateBlockProcessor}.
	 */
	private final NPCRenderUpdate npcUpdate = new NPCRenderUpdate();

	/**
	 * Represents the {@code Appearance} to use for this {@code UpdateBlockProcessor}.
	 */
	private final Appearance appearance;

	/**
	 * Represents the {@code Animator} to use for this {@code UpdateBlockProcessor}.
	 */
	private final Animator animator;

	/**
	 * Constructs a new {@code UpdateBlockProcessor} {@code Object}.
	 * 
	 * @param entity The {@code Entity} to use.
	 */
	public UpdateBlockProcessor(Entity entity) {
		this.entity = entity;
		appearance = new Appearance(entity);
		animator = new Animator(entity);
	}

	/**
	 * Processes the {@code PlayerRenderUpdate}.
	 */
	public IoWriteEvent processPlayerUpdate() {
		return playerUpdate.processPlayerUpdate((Player) entity);
	}

	/**
	 * Processes the {@code NPCRenderUpdate}.
	 */
	public IoWriteEvent processNPCUpdate() {
		return npcUpdate.processNPCUpdate((NPC) entity);
	}

	/**
	 * Gets the flags.
	 * 
	 * @return the flags
	 */
	public BitSet getFlags() {
		return flags;
	}

	/**
	 * Gets the player update.
	 * 
	 * @return the playerUpdate
	 */
	public PlayerRenderUpdate getPlayerUpdate() {
		return playerUpdate;
	}

	/**
	 * Gets the npc update.
	 * 
	 * @return the npcUpdate
	 */
	public NPCRenderUpdate getNpcUpdate() {
		return npcUpdate;
	}

	/**
	 * Gets the appearance.
	 * 
	 * @return the appearance
	 */
	public Appearance getAppearance() {
		return appearance;
	}

	/**
	 * Gets the animator.
	 * 
	 * @return the animator
	 */
	public Animator getAnimator() {
		return animator;
	}

	/**
	 * Gets if an update is required.
	 * 
	 * @return
	 */
	public boolean isUpdateRequired() {
		return !flags.isEmpty();
	}

	/**
	 * Flags a update.
	 * 
	 * @param updateFlag The {@code UpdateFlag}.
	 */
	public void flagUpdate(UpdateFlag updateFlag) {
		flags.set(updateFlag.ordinal());
	}

	/**
	 * Gets if theres a pending flag.
	 * 
	 * @param flag The flag to get the value of.
	 * @return
	 */
	public boolean hasFlag(UpdateFlag flag) {
		return flags.get(flag.ordinal());
	}

	/**
	 * @author _Jordan <citellumrsps@gmail.com>
	 * @since Apr 19, 2015
	 */
	public enum UpdateFlag {

		/**
		 * Represents the appearance update flag.
		 */
		APPEARANCE,

		/**
		 * Represents the animation update flag.
		 */
		ANIMATION,

		/**
		 * Represents the graphic update flag.
		 */
		GRAPHIC;

	}

}
