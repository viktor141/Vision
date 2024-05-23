package com.cifrazia.vision.core.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BuyItemPacket implements IMessage {
    private String jsonItems;
    private String name;

    public BuyItemPacket() {
    }

    public BuyItemPacket(String jsonItems, String name) {
        this.jsonItems = jsonItems;
        this.name = name;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        jsonItems = ByteBufUtils.readUTF8String(buf);
        name = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, jsonItems);
        ByteBufUtils.writeUTF8String(buf, name);
    }

    public String getJsonItems() {
        return jsonItems;
    }

    public String getName() {
        return name;
    }
}
