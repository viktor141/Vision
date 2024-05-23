package com.cifrazia.vision.core.network.packets.server;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ServerWarehouseRetrievePacket implements IMessage {
    private int[] items;

    public ServerWarehouseRetrievePacket() {
    }

    public ServerWarehouseRetrievePacket(int[] items) {
        this.items = items;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        items = new int[size];
        for (int i = 0; i < size; i++) {
            items[i] = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(items.length);
        for (int item : items) {
            buf.writeInt(item);
        }
    }

    public int[] getItems() {
        return items;
    }
}
