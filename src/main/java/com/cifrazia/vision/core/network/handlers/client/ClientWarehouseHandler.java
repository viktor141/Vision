package com.cifrazia.vision.core.network.handlers.client;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.core.network.packets.client.ClientWarehouseResultPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientWarehouseHandler implements IMessageHandler<ClientWarehouseResultPacket, IMessage> {
    @Override
    public IMessage onMessage(ClientWarehouseResultPacket message, MessageContext ctx) {
        ((AuthorizedClient) Vision.getInstance().getAuthorization()).getWarehouseData().forceUpdate();

        return null;
    }
}
