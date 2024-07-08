package com.cifrazia.vision.core.network.packets.client;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ClientBalanceResponsePacket implements IMessage {
    private long ruby;
    private long gold;


    public ClientBalanceResponsePacket() {
    }

    public ClientBalanceResponsePacket(long ruby, long gold) {
        this.ruby = ruby;
        this.gold = gold;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        ruby = buf.readLong();
        gold = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(ruby);
        buf.writeLong(gold);
    }

    public long getRuby() {
        return ruby;
    }

    public long getGold() {
        return gold;
    }
}
