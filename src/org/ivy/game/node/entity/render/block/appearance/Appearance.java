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
package org.ivy.game.node.entity.render.block.appearance;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ivy.game.node.entity.Entity;
import org.ivy.game.node.entity.player.Player;
import org.ivy.game.node.entity.render.block.UpdateBlock;
import org.ivy.game.node.entity.render.block.appearance.impl.Arms;
import org.ivy.game.node.entity.render.block.appearance.impl.Chest;
import org.ivy.game.node.entity.render.block.appearance.impl.Feet;
import org.ivy.game.node.entity.render.block.appearance.impl.Hands;
import org.ivy.game.node.entity.render.block.appearance.impl.Hat;
import org.ivy.game.node.entity.render.block.appearance.impl.Legs;
import org.ivy.game.node.entity.render.block.appearance.impl.Shield;
import org.ivy.game.node.entity.render.block.appearance.impl.Skin;
import org.ivy.game.node.entity.render.block.appearance.impl.Torso;

import com.runescape.ioheap.IoWriteEvent;

import utilities.buffer.ByteBufferUtils;
import utilities.cryption.md5.MD5Encryption;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Mar 1, 2015
 */
public class Appearance extends UpdateBlock {

	/**
	 * Represents the data of this {@code Appearance}.
	 */
	private byte[] appeareanceData;

	/**
	 * Represents the encrypted data of this {@code Appearance}.
	 */
	private byte[] md5AppeareanceDataHash;

	/**
	 * Constructs a new {@code Appearance} {@code Object}.
	 * 
	 * @param entity The {@code Entity} to use.
	 */
	public Appearance(Entity entity) {
		super(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.game.node.entity.render.block.UpdateBlock#buildUpdateBlock(com.runescape.ioheap.IoWriteEvent)
	 */
	@Override
	public void buildUpdateBlock(IoWriteEvent block) {
		if (entity instanceof Player) {
			ByteBuf buffer = Unpooled.buffer();
			int mask = 0x0;
			buffer.writeByte(mask);

			/**
			 * This writes the title id of the player.
			 */
			buffer.writeByte(0);

			/**
			 * This writes the head icon of the player.
			 */
			buffer.writeByte(-1);

			/**
			 * This writes the wilderness skull icon.
			 */
			buffer.writeByte(-1);

			/**
			 * This writes whether the player is hidden or not.
			 */
			buffer.writeByte(0);

			/**
			 * Writes the first four equipment slots.
			 */
			for (int index = 0; index < 4; index++) {
				buffer.writeByte(0);
			}

			/**
			 * Writes the appearance body data of the player.
			 */
			for (Entry<Class<?>, BodyAppearance> i : BODY_APPEARANCE.entrySet()) {
				if (i.getKey().equals(Skin.class)) {
					continue;
				}
				i.getValue().buildBodyPart(buffer, ((Player) entity));
			}

			/**
			 * Extra stuff needed, we could honestly just send a short and not have all of this. But we'll see.
			 */
			buffer.writeShort(0);

			/**
			 * Writes the different color styles to the appearance.
			 * 
			 */
			buffer.writeByte(BODY_APPEARANCE.get(Hat.class).getColorId());
			buffer.writeByte(BODY_APPEARANCE.get(Torso.class).getColorId());
			buffer.writeByte(BODY_APPEARANCE.get(Legs.class).getColorId());
			buffer.writeByte(BODY_APPEARANCE.get(Feet.class).getColorId());
			buffer.writeByte(BODY_APPEARANCE.get(Skin.class).getColorId());

			/**
			 * This writes the player's animation.
			 */
			buffer.writeShort(1426);

			/**
			 * This writes the player's username/displayname.
			 */
			System.out.println(((Player) entity).getAccount().getUsername());
			ByteBufferUtils.writeRS2String(((Player) entity).getAccount().getUsername(), buffer);

			/**
			 * This writes the player's combat level.
			 */
			buffer.writeByte(138);

			/**
			 * This writes the player's total level.
			 */
			buffer.writeShort(2496);
			buffer.writeByte(0);

			/**
			 * Player render update stuff used from appearance.
			 */
			byte[] data = new byte[buffer.writerIndex()];
			System.arraycopy(buffer.array(), 0, data, 0, data.length);
			byte[] encryption = MD5Encryption.encryptMD5(data);
			this.appeareanceData = data;
			this.md5AppeareanceDataHash = encryption;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ivy.game.node.entity.render.flag.UpdateBlock#getMask()
	 */
	@Override
	public int getMask() {
		return entity instanceof Player ? 0x4 : -1;
	}

	/**
	 * Gets the appearance data.
	 * 
	 * @return the appeareanceData
	 */
	public byte[] getAppeareanceData() {
		return appeareanceData;
	}

	/**
	 * Sets the appearance data.
	 * 
	 * @param appeareanceData the appeareanceData to set
	 */
	public void setAppeareanceData(byte[] appeareanceData) {
		this.appeareanceData = appeareanceData;
	}

	/**
	 * Gets the appearance data hash.
	 * 
	 * @return the md5AppeareanceDataHash
	 */
	public byte[] getMd5AppeareanceDataHash() {
		return md5AppeareanceDataHash;
	}

	/**
	 * Sets the appearance data hash.`
	 * 
	 * @param md5AppeareanceDataHash the md5AppeareanceDataHash to set
	 */
	public void setMd5AppeareanceDataHash(byte[] md5AppeareanceDataHash) {
		this.md5AppeareanceDataHash = md5AppeareanceDataHash;
	}

	/**
	 * Represents the body to use for the {@code Appearance}.
	 */
	private static final Map<Class<?>, BodyAppearance> BODY_APPEARANCE = new HashMap<Class<?>, BodyAppearance>();

	/**
	 * Gets the body of the {@code Appearance}.
	 * 
	 * @return the body
	 */
	public Map<Class<?>, BodyAppearance> getBodyAppearance() {
		return BODY_APPEARANCE;
	}

	static {
		BODY_APPEARANCE.put(Torso.class, new Torso(459));
		BODY_APPEARANCE.put(Shield.class, new Shield(0));
		BODY_APPEARANCE.put(Arms.class, new Arms(26));
		BODY_APPEARANCE.put(Legs.class, new Legs(646));
		BODY_APPEARANCE.put(Hat.class, new Hat(268));
		BODY_APPEARANCE.put(Hands.class, new Hands(34));
		BODY_APPEARANCE.put(Feet.class, new Feet(440));
		BODY_APPEARANCE.put(Chest.class, new Chest(14));
		BODY_APPEARANCE.put(Skin.class, new Skin());
	}

}
