package com.cifrazia.vision;

import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CustomPingServerHandler implements IMessageHandler<PacketCustomRequest, PacketCustomResponse> {

    @Override
    public PacketCustomResponse onMessage(PacketCustomRequest message, MessageContext ctx) {
        // Обработка запроса и отправка ответа
        System.out.println("Received custom request: " + message.getMessage());
        return new PacketCustomResponse("Ответ от сервера на кастомный запрос: " + message.getMessage());
    }
}
