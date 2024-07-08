package com.cifrazia.vision.core.network.handlers.client;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.core.network.packets.client.ClientBalanceResponsePacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientBalanceHandler implements IMessageHandler<ClientBalanceResponsePacket, IMessage> {


    @Override
    public IMessage onMessage(ClientBalanceResponsePacket message, MessageContext ctx) {
        AuthorizedClient auth = (AuthorizedClient) Vision.getInstance().getAuthorization();

        auth.getPlayerCifraziaData().setBalanceData(message.getRuby(), message.getGold());

        return null;
    }
}
