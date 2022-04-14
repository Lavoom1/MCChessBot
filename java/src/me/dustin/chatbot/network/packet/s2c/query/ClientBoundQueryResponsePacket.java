package me.dustin.chatbot.network.packet.s2c.query;

import me.dustin.chatbot.network.packet.Packet;
import me.dustin.chatbot.network.packet.handler.ClientBoundPacketHandler;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;

public class ClientBoundQueryResponsePacket extends Packet.ClientBoundPacket {

    private final String jsonData;

    public ClientBoundQueryResponsePacket(PacketByteBuf packetByteBuf) {
        super(packetByteBuf);
        this.jsonData = packetByteBuf.readString(32767);
    }

    @Override
    public void apply(ClientBoundPacketHandler clientBoundPacketHandler) {

    }

    public String getJsonData() {
        return jsonData;
    }
}
