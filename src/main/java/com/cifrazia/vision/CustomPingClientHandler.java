package com.cifrazia.vision;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CustomPingClientHandler implements IMessageHandler<PacketCustomResponse, IMessage> {

    @Override
    public IMessage onMessage(PacketCustomResponse message, MessageContext ctx) {
        // Обработка ответа на клиенте
        System.out.println("Received response from server: " + message.getMessage());
        return null; // Нет необходимости отправлять ответ на ответ
    }
}
