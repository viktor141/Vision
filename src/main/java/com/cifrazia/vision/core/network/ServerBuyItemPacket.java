package com.cifrazia.vision.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ServerBuyItemPacket implements IMessage {
    private String jsonItems;

    public ServerBuyItemPacket() {
    }

    public ServerBuyItemPacket(String jsonItems) {
        this.jsonItems = jsonItems;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        jsonItems = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, jsonItems);
    }

    public String getJsonItems() {
        return jsonItems;
    }
}
