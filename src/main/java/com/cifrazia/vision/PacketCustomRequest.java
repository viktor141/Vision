package com.cifrazia.vision;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketCustomRequest implements IMessage {
    private String message;

    public PacketCustomRequest() {}

    public PacketCustomRequest(String message) {
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        this.message = new String(bytes);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] bytes = message.getBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public String getMessage() {
        return message;
    }
}
