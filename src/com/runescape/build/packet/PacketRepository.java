package com.runescape.build.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.runescape.build.packet.context.PacketContext;
import com.runescape.build.packet.context.impl.ChatPacketContext;
import com.runescape.build.packet.context.impl.ConfigPacketContext;
import com.runescape.build.packet.context.impl.GlobalConfigPacketContext;
import com.runescape.build.packet.context.impl.InterfacePacketContext;
import com.runescape.build.packet.context.impl.LandscapePacketContext;
import com.runescape.build.packet.context.impl.MessagePacketContext;
import com.runescape.build.packet.context.impl.PingPacketContext;
import com.runescape.build.packet.context.impl.PlayerUpdateContext;
import com.runescape.build.packet.context.impl.WebsitePacketContext;
import com.runescape.build.packet.context.impl.WindowPacketContext;
import com.runescape.build.packet.context.impl.WorldListPacketContext;
import com.runescape.build.packet.decode.PacketDecoder;
import com.runescape.build.packet.decode.impl.ButtonPacketDecoder;
import com.runescape.build.packet.decode.impl.ChatPacketDecoder;
import com.runescape.build.packet.decode.impl.DisplayPacketDecoder;
import com.runescape.build.packet.decode.impl.PingPacketDecoder;
import com.runescape.build.packet.decode.impl.WorldListDecoder;
import com.runescape.build.packet.encode.PacketEncoder;
import com.runescape.build.packet.encode.impl.ChatPacketEncoder;
import com.runescape.build.packet.encode.impl.ConfigPacketEncoder;
import com.runescape.build.packet.encode.impl.GlobalConfigPacketEncoder;
import com.runescape.build.packet.encode.impl.InterfacePacketEncoder;
import com.runescape.build.packet.encode.impl.LandscapePacketEncoder;
import com.runescape.build.packet.encode.impl.MessagePacketEncoder;
import com.runescape.build.packet.encode.impl.PingPacketEncoder;
import com.runescape.build.packet.encode.impl.PlayerUpdateEncoder;
import com.runescape.build.packet.encode.impl.WebsitePacketEncoder;
import com.runescape.build.packet.encode.impl.WindowPacketEncoder;
import com.runescape.build.packet.encode.impl.WorldListPacketEncoder;
import com.runescape.ioheap.IoWriteEvent;

/**
 * @author Emperor
 */
public class PacketRepository {

	/**
	 * Represents a composite dump of all the decoding packets.
	 */
	private static final Map<int[], PacketDecoder> DECODING_PACKETS = new HashMap<int[], PacketDecoder>(93);

	/**
	 * Represents a composite dump of all the encoding packets.
	 */
	private static final Map<Class<?>, PacketEncoder<? extends PacketContext>> ENCODING_PACKETS = new HashMap<Class<?>, PacketEncoder<? extends PacketContext>>(141);

	static {
		/* Decoding Packets */
		DECODING_PACKETS.put(new int[] { 0 }, new PingPacketDecoder());
		DECODING_PACKETS.put(new int[] { 45 }, new WorldListDecoder());
		// // DECODING_PACKETS.put(0, new LinkPacketDecoder());
		DECODING_PACKETS.put(new int[] { 38 }, new ChatPacketDecoder());
		DECODING_PACKETS.put(new int[] { 69 }, new DisplayPacketDecoder());
		DECODING_PACKETS.put(new int[] { 16, 52 }, new ButtonPacketDecoder());

		/* Encoding Packets */
		ENCODING_PACKETS.put(PingPacketContext.class, new PingPacketEncoder());
		ENCODING_PACKETS.put(MessagePacketContext.class, new MessagePacketEncoder());
		ENCODING_PACKETS.put(WorldListPacketContext.class, new WorldListPacketEncoder());
		ENCODING_PACKETS.put(WebsitePacketContext.class, new WebsitePacketEncoder());
		ENCODING_PACKETS.put(WindowPacketContext.class, new WindowPacketEncoder());
		ENCODING_PACKETS.put(LandscapePacketContext.class, new LandscapePacketEncoder());
		ENCODING_PACKETS.put(InterfacePacketContext.class, new InterfacePacketEncoder());
		ENCODING_PACKETS.put(ConfigPacketContext.class, new ConfigPacketEncoder());
		ENCODING_PACKETS.put(GlobalConfigPacketContext.class, new GlobalConfigPacketEncoder());
		ENCODING_PACKETS.put(PlayerUpdateContext.class, new PlayerUpdateEncoder());
		ENCODING_PACKETS.put(ChatPacketContext.class, new ChatPacketEncoder());
	}

	/**
	 * Writes an encoding packet.
	 * 
	 * @param context The packet encoding context.
	 * @return The created packet.
	 */
	@SuppressWarnings("unchecked")
	public static IoWriteEvent encodePacket(PacketContext context) {
		PacketEncoder<PacketContext> packet = (PacketEncoder<PacketContext>) ENCODING_PACKETS.get(context.getClass());
		if (packet != null) {
			return packet.encodePacket(context);
		}
		return null;
	}

	/**
	 * Gets a {@code PacketDecoder} based on a requested packet id.
	 * 
	 * @param packetId The packet id to use.
	 * @return The decoded packet.
	 */
	public static PacketDecoder getDecodingPacket(int packetId) {
		for (Entry<int[], PacketDecoder> i : DECODING_PACKETS.entrySet()) {
			for (int x = 0; x < i.getKey().length; x++) {
				if (i.getKey()[x] == packetId) {
					return i.getValue();
				}
			}
		}
		return null;
	}

}
