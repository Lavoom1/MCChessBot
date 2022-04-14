package me.dustin.chatbot.network.packet.s2c.play;

import me.dustin.chatbot.network.packet.Packet;
import me.dustin.chatbot.network.packet.ProtocolHandler;
import me.dustin.chatbot.network.packet.handler.ClientBoundPacketHandler;
import me.dustin.chatbot.network.packet.handler.PlayClientBoundPacketHandler;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;

import java.util.UUID;

public class ClientBoundSpawnMobPacket extends Packet.ClientBoundPacket {
    private final int entityId;
    private final UUID uuid;
    private final int type;
    private final double x, y, z;
    private final byte yaw, pitch;
    private final byte headYaw;
    private final short velocityX, velocityY, velocityZ;

    public ClientBoundSpawnMobPacket(PacketByteBuf packetByteBuf) {
        super(packetByteBuf);
        this.entityId = packetByteBuf.readVarInt();
        this.uuid = packetByteBuf.readUuid();
        this.type = ProtocolHandler.getCurrent().getProtocolVer() <= ProtocolHandler.getVersionFromName("1.10.2").getProtocolVer() ? packetByteBuf.readByte() : packetByteBuf.readVarInt();
        if (ProtocolHandler.getCurrent().getProtocolVer() <= ProtocolHandler.getVersionFromName("1.8.9").getProtocolVer()) {
            this.x = (packetByteBuf.readInt() / 32.D);
            this.y = (packetByteBuf.readInt() / 32.D);
            this.z = (packetByteBuf.readInt() / 32.D);
        } else {
            this.x = packetByteBuf.readDouble();
            this.y = packetByteBuf.readDouble();
            this.z = packetByteBuf.readDouble();
        }
        this.yaw = packetByteBuf.readByte();
        this.pitch = packetByteBuf.readByte();
        this.headYaw = packetByteBuf.readByte();
        this.velocityX = packetByteBuf.readShort();
        this.velocityY = packetByteBuf.readShort();
        this.velocityZ = packetByteBuf.readShort();
    }

    @Override
    public void apply(ClientBoundPacketHandler clientBoundPacketHandler) {
        ((PlayClientBoundPacketHandler)clientBoundPacketHandler).handleSpawnMobPacket(this);
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public byte getHeadYaw() {
        return headYaw;
    }

    public short getVelocityX() {
        return velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }
}
