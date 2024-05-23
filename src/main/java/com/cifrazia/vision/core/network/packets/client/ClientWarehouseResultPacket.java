package com.cifrazia.vision.core.network.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ClientWarehouseResultPacket implements IMessage {
    private int resultCode;

    public ClientWarehouseResultPacket() {
    }

    public ClientWarehouseResultPacket(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        resultCode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(resultCode);
    }
}
