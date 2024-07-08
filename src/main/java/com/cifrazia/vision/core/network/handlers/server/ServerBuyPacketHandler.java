package com.cifrazia.vision.core.network.handlers.server;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedServer;
import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.cifrazia.vision.connection.data.request.BuyItemRequest;
import com.cifrazia.vision.core.misc.MessageParser;
import com.cifrazia.vision.core.network.packets.BuyItemPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.concurrent.CompletableFuture;

public class ServerBuyPacketHandler implements IMessageHandler<BuyItemPacket, IMessage> {

    @Override
    public IMessage onMessage(BuyItemPacket packet, MessageContext ctx) {
        RequestItem[] requestItems = MessageParser.getJsonObject(packet.getJsonItems());
        EntityPlayerMP player = ctx.getServerHandler().player;

        AuthorizedServer auth = (AuthorizedServer) Vision.getInstance().getAuthorization();
        CompletableFuture.supplyAsync(() -> auth.userBuyItems(new BuyItemRequest(EntityPlayer.getUUID(player.getGameProfile()).toString(), requestItems)))
                .thenAccept(result -> {

                    if (result != null) {
                        Vision.getInstance().getNetwork().sendTo(packet, player);
                    } else {
                        Vision.getInstance().getNetwork().sendTo(new BuyItemPacket(MessageParser.getJsonObject(new RequestItem[]{}), null), player);
                    }
                });

        return null;
    }
}
