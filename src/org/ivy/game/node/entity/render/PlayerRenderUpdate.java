package org.ivy.game.node.entity.render;

import java.security.MessageDigest;

import org.ivy.game.node.entity.player.Player;
import org.ivy.game.world.impl.GameWorld;

import com.runescape.ioheap.IoWriteEvent;

public class PlayerRenderUpdate {

	private byte[] updateFlags;
	private int[] regionHashes;
	private byte[][] appearanceHashes;

	/**
	 * Represents the local players index count.
	 */
	private int localIndexCount;

	/**
	 * Represents the global players index count.
	 */
	private int globalIndexCount;

	/**
	 * Represents the player update data.
	 */
	private int dataLength;

	/**
	 * Constructs a new {@code PlayerRenderUpdate} {@code Object}.
	 */
	public PlayerRenderUpdate() {
		updateFlags = new byte[2048];
		appearanceHashes = new byte[2500][];
		regionHashes = new int[2048];
	}

	/**
	 * Sets a requested {@code Player} for this {@code PlayerRenderUpdate}.
	 * 
	 * @param player The {@code Player} to set.
	 * @param buffer The {@code PacketBufferEncoder} to use.
	 */
	public void enterGameWorld(Player player, IoWriteEvent buffer) {
		buffer.initBitAccess();
		buffer.writeBits(30, player.getTileHash());
		player.getLocationManager().getViewport().getLocalPlayers()[player.getIndex()] = player;
		player.getLocationManager().getViewport().getLocalIndexes()[localIndexCount++] = player.getIndex();
		for (int index = 1; index < GameWorld.MAX_PLAYER_CAP; index++) {
			if (index == player.getIndex()) {
				continue;
			}
			buffer.writeBits(18, 0);
			player.getLocationManager().getViewport().getGlobalIndexes()[globalIndexCount++] = index;
		}
		buffer.finishBitAccess();
	}

	/**
	 * Sends the {@code PlayerRenderpdate}.
	 * 
	 * @param player The {@code Player} to use for the render update.
	 */
	public IoWriteEvent processPlayerUpdate(Player player) {
		IoWriteEvent buffer = IoWriteEvent.create(0);
		IoWriteEvent block = IoWriteEvent.create();
		processLocalPlayers(player, buffer, block, true);
		processLocalPlayers(player, buffer, block, false);
		processGlobalPlayers(player, buffer, block, true);
		processGlobalPlayers(player, buffer, block, false);
		buffer.transfer(block);
		dataLength = 0;
		localIndexCount = 0;
		globalIndexCount = 0;
		for (int index = 1; index < GameWorld.MAX_PLAYER_CAP; index++) {
			updateFlags[index] >>= 1;
			Player p = player.getLocationManager().getViewport().getLocalPlayers()[index];
			if (p == null) {
				player.getLocationManager().getViewport().getGlobalIndexes()[globalIndexCount++] = index;
			} else {
				player.getLocationManager().getViewport().getLocalIndexes()[localIndexCount++] = index;
			}
		}
		return buffer;
	}

	/**
	 * Processes the global players of this {@code PlayerRenderUpdate}.
	 * 
	 * @param buffer The {@code PacketBufferEncoder} for buffering the render update.
	 * @param block The {@code PacketBufferEncoder} for queuing the render update block.
	 * @param nsn2
	 */
	private void processGlobalPlayers(Player player, IoWriteEvent buffer, IoWriteEvent block, boolean nsn2) {
		buffer.initBitAccess();
		int skip = 0;
		for (int globalCount = 0; globalCount < globalIndexCount; globalCount++) {
			int countIndex = player.getLocationManager().getViewport().getGlobalIndexes()[globalCount];
			if (nsn2 ? (0x1 & updateFlags[countIndex]) == 0 : (0x1 & updateFlags[countIndex]) != 0)
				continue;
			if (skip > 0) {
				skip--;
				updateFlags[countIndex] = (byte) (updateFlags[countIndex] | 2);
				continue;
			}
			int regionHash = player == null ? regionHashes[countIndex] : player.getRegionHash();
			if (player != null && regionHash != regionHashes[countIndex]) {
				buffer.writeBits(1, 1);
				renderRegionHash(buffer, regionHashes[countIndex], regionHash);
				regionHashes[countIndex] = regionHash;
			} else {
				buffer.writeBits(1, 0);
				for (int globalIndex = globalCount + 1; globalIndex < globalIndexCount; globalIndex++) {
					int index = player.getLocationManager().getViewport().getGlobalIndexes()[globalIndex];
					if (nsn2 ? (0x1 & updateFlags[index]) == 0 : (0x1 & updateFlags[index]) != 0) {
						continue;
					}
					Player p2 = player;
					if (/* needsQueued(p2) || */(p2 != null && p2.getRegionHash() != regionHashes[index])) {
						break;
					}
					skip++;
				}
				skipPlayers(buffer, skip);
				updateFlags[countIndex] = (byte) (updateFlags[countIndex] | 2);
			}
		}
	}

	/**
	 * Processes the local players of this {@code PlayerRenderUpdate}.
	 * 
	 * @param buffer The {@code PacketBufferEncoder} for buffering the render update.
	 * @param block The {@code PacketBufferEncoder} for queuing the render update block.
	 * @param nsn0
	 */
	private void processLocalPlayers(Player player, IoWriteEvent buffer, IoWriteEvent block, boolean nsn0) {
		buffer.initBitAccess();
		int skip = 0;
		for (int localCount = 0; localCount < localIndexCount; localCount++) {
			int countIndex = player.getLocationManager().getViewport().getLocalIndexes()[localCount];
			if (nsn0 ? (0x1 & updateFlags[countIndex]) != 0 : (0x1 & updateFlags[countIndex]) == 0) {
				continue;
			}
			if (skip > 0) {
				skip--;
				updateFlags[countIndex] = (byte) (updateFlags[countIndex] | 2);
				continue;
			}
			Player localPlayer = player.getLocationManager().getViewport().getLocalPlayers()[countIndex];
			boolean needAppearenceUpdate = appearanceUpdateRequired(localPlayer.getIndex(), localPlayer.getFlagProcessor().getAppearance().getMd5AppeareanceDataHash());
			boolean needUpdate = needAppearenceUpdate;
			if (needUpdate) {
				appendUpdateBlock(localPlayer, block, needAppearenceUpdate, false);
			}
			if (needUpdate) {
				buffer.writeBits(1, 1);
				buffer.writeBits(1, 1);
				buffer.writeBits(2, 0);
			} else {
				buffer.writeBits(1, 0);
				for (int localIndex = localCount + 1; localIndex < localIndexCount; localIndex++) {
					int index = player.getLocationManager().getViewport().getLocalIndexes()[localIndex];
					if (nsn0 ? (0x1 & updateFlags[index]) != 0 : (0x1 & updateFlags[index]) == 0) {
						continue;
					}
					Player p2 = localPlayer.getLocationManager().getViewport().getLocalPlayers()[index];
					if (appearanceUpdateRequired(p2.getIndex(), localPlayer.getFlagProcessor().getAppearance().getMd5AppeareanceDataHash())) {
						break;
					}
					skip++;
				}
				skipPlayers(buffer, skip);
				updateFlags[countIndex] = (byte) (updateFlags[countIndex] | 2);
			}
		}
		buffer.finishBitAccess();
	}

	/**
	 * Appends a flag update.
	 * 
	 * @param player The {@code Player} to use.
	 * @param block The {@code PacketBufferEncoder} to use.
	 * @param needAppearenceUpdate If the {@code Appearance} is requiring an update.
	 * @param b
	 */
	private void appendUpdateBlock(Player player, IoWriteEvent block, boolean needAppearenceUpdate, boolean b) {
		int mask = 0x0;
		if (needAppearenceUpdate) {
			mask |= player.getFlagProcessor().getAppearance().getMask();
		}
		if (mask >= 0x100) {
			mask |= 0x20;
			if (mask >= 0x10000) {
				mask |= 0x8000;
				block.write(mask & 0xff);
				block.write((mask >> 8) & 0xffff);
				block.write(mask >> 16);
			} else {
				block.write(mask & 0xff);
				block.write(mask >> 8);
			}
		} else {
			block.write(mask & 0xff);
		}
		if (needAppearenceUpdate) {
			if (player.getFlagProcessor().getAppearance().getAppeareanceData() == null) {
				player.getFlagProcessor().processAppearance();
			}
			byte[] renderData = player.getFlagProcessor().getAppearance().getAppeareanceData();
			block.writeS(renderData.length);
			block.writeBytes(renderData, 0, renderData.length);
		}
	}

	/**
	 * Skips players.
	 * 
	 * @param buffer The {@code PacketBufferEncoder} to use.
	 * @param skip The amount to skip.
	 */
	private void skipPlayers(IoWriteEvent buffer, int skip) {
		buffer.writeBits(2, skip == 0 ? 0 : skip > 255 ? 3 : (skip > 31 ? 2 : 1));
		if (skip > 0) {
			buffer.writeBits(skip > 255 ? 11 : (skip > 31 ? 8 : 5), skip);
		}
	}

	/**
	 * Checks if an {@code Appearance} update is required.
	 * 
	 * @param index The index of the {@code Player}.
	 * @param hash The hashed data of the {@code Appearance}.
	 * @return
	 */
	private boolean appearanceUpdateRequired(int index, byte[] hash) {
		if (dataLength > ((7500 - 500) / 2) || hash == null) {
			return false;
		}
		return appearanceHashes[index] == null || !MessageDigest.isEqual(appearanceHashes[index], hash);
	}

	/**
	 * 
	 * @param buffer
	 * @param lastRegionHash
	 * @param currentRegionHash
	 */
	private void renderRegionHash(IoWriteEvent buffer, int lastRegionHash, int currentRegionHash) {
		int lastRegionX = lastRegionHash >> 8;
		int lastRegionY = 0xff & lastRegionHash;
		int lastPlane = lastRegionHash >> 16;
		int currentRegionX = currentRegionHash >> 8;
		int currentRegionY = 0xff & currentRegionHash;
		int currentPlane = currentRegionHash >> 16;
		int planeOffset = currentPlane - lastPlane;
		if (lastRegionX == currentRegionX && lastRegionY == currentRegionY) {
			buffer.writeBits(2, 1);
			buffer.writeBits(2, planeOffset);
		} else if (Math.abs(currentRegionX - lastRegionX) <= 1 && Math.abs(currentRegionY - lastRegionY) <= 1) {
			int opcode;
			int dx = currentRegionX - lastRegionX;
			int dy = currentRegionY - lastRegionY;
			if (dx == -1 && dy == -1) {
				opcode = 0;
			} else if (dx == 1 && dy == -1) {
				opcode = 2;
			} else if (dx == -1 && dy == 1) {
				opcode = 5;
			} else if (dx == 1 && dy == 1) {
				opcode = 7;
			} else if (dy == -1) {
				opcode = 1;
			} else if (dx == -1) {
				opcode = 3;
			} else if (dx == 1) {
				opcode = 4;
			} else {
				opcode = 6;
			}
			buffer.writeBits(2, 2);
			buffer.writeBits(5, (planeOffset << 3) + (opcode & 0x7));
		} else {
			int xOffset = currentRegionX - lastRegionX;
			int yOffset = currentRegionY - lastRegionY;
			buffer.writeBits(2, 3);
			buffer.writeBits(18, (yOffset & 0xff) + ((xOffset & 0xff) << 8) + (planeOffset << 16));
		}
	}

}
