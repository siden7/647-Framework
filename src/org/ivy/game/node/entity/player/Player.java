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

import org.ivy.connect.state.impl.WorldGameState;
import org.ivy.game.node.entity.Entity;
import org.ivy.game.node.entity.player.managers.display.ComponentManager;
import org.ivy.game.node.entity.player.managers.display.DisplayManager;
import org.ivy.game.node.entity.player.managers.location.LocationManager;
import org.ivy.game.node.entity.render.block.UpdateBlockProcessor.UpdateFlag;

import com.runescape.RuneScape;
import com.runescape.build.packet.PacketProcessor;
import com.runescape.build.protocol.context.LoginRequestContext;

/**
 * @author _Jordan <citellumrsps@gmail.com>
 * @since Feb 25, 2015
 */
public class Player extends Entity {

	/**
	 * Represents the {@code GameState} to use.
	 */
	private WorldGameState gameState;

	/**
	 * Represents the {@code Account} to use.
	 */
	private final Account account;

	/**
	 * Represents the {@code PacketProcessor} to use.
	 */
	private final PacketProcessor packetProcessor = new PacketProcessor(this);

	/**
	 * Represents the {@code LocationManager} to use.
	 */
	private final LocationManager locationManager = new LocationManager(this);

	/**
	 * Represents the {@code DisplayManager} to use.
	 */
	private final DisplayManager displayManager = new DisplayManager(this);

	/**
	 * Represents the {@code InterfaceManager} to use.
	 */
	private final ComponentManager interfaceManager = new ComponentManager(this);

	/**
	 * Represents if the {@code Player} finished login.
	 */
	private boolean finishedLogin;

	/**
	 * Constructs a new {@code Player} {@code Object}.
	 * 
	 * @param gameState The {@code GameState} to use for this {@code Player}.
	 * @param account The account to use for the {@code Player}.
	 */
	public Player(Account account) {
		this.account = account;
	}

	/**
	 * Sends the login of the {@code Player}.
	 * 
	 * @param request The type of {@code LoginRequestContext}.
	 */
	public void sendLogin(WorldGameState gameState, LoginRequestContext request) {
		this.gameState = gameState;
		switch (request.getLoginRequest()) {
		case REQUEST_LOBBY:
			break;
		case REQUEST_WORLD:
			RuneScape.getGameContext().getWorld().addPlayer(this);// TODO Probs find a better way of handling related stuff like this.
			locationManager.refreshManager();
			updateBlockProcessor.getAppearance().buildUpdateBlock(null);
			updateBlockProcessor.flagUpdate(UpdateFlag.APPEARANCE);
			packetProcessor.processPlayerUpdate();
			packetProcessor.processConfig(1240, 1980);// HP
			packetProcessor.processConfig(1801, 13370);// XP gained.
			packetProcessor.processConfig(1160, -1);// Summoning orb
			packetProcessor.processConfig(313, -1);// Emotes
			packetProcessor.processConfig(465, 7);// Goblin emotes
			packetProcessor.processConfig(802, -1);// Stronghold of security.
			packetProcessor.processConfig(1085, 249852);// Zombie hand emote
			packetProcessor.processConfig(1404, 123728213);// Around the world in eggty days
			packetProcessor.processConfig(1597, -1);// Dramatic point
			packetProcessor.processConfig(1842, -1);// Faint
			packetProcessor.processConfig(1921, -893736236);// Puppet master
			packetProcessor.processConfig(2033, 1043648799);// Seal of approval
			break;
		}
	}

	/**
	 * Gets the GameState.
	 * 
	 * @return the GameState
	 */
	public WorldGameState getGameState() {
		return gameState;
	}

	/**
	 * Gets the account.
	 * 
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Gets the packet processor.
	 * 
	 * @return the packetProcessor
	 */
	public PacketProcessor getPacketProcessor() {
		return packetProcessor;
	}

	/**
	 * Gets the location manager.
	 * 
	 * @return the locationManager
	 */
	public LocationManager getLocationManager() {
		return locationManager;
	}

	/**
	 * Gets the display manager.
	 * 
	 * @return the displayManager
	 */
	public DisplayManager getDisplayManager() {
		return displayManager;
	}

	/**
	 * Gets the interface manager.
	 * 
	 * @return the interfaceManager
	 */
	public ComponentManager getInterfaceManager() {
		return interfaceManager;
	}

	/**
	 * Gets if the player has finished login.
	 * 
	 * @return the finishedLogin
	 */
	public boolean hasFinishedLogin() {
		return finishedLogin;
	}

	/**
	 * Sets if the player has finished login.
	 * 
	 * @param finishedLogin the finishedLogin to set
	 */
	public void setFinishedLogin(boolean finishedLogin) {
		this.finishedLogin = finishedLogin;
	}

}
