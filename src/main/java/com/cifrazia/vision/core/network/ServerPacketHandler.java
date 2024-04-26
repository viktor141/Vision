package com.cifrazia.vision.core.network;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedServer;
import com.cifrazia.vision.connection.data.BuyItemRequest;
import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.cifrazia.vision.core.misc.MessageParser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;

public class ServerPacketHandler implements IMessageHandler<ServerBuyItemPacket, IMessage> {

    @Override
    public IMessage onMessage(ServerBuyItemPacket packet, MessageContext ctx) {
        RequestItem[] requestItems = MessageParser.getJsonObject(packet.getJsonItems());
        EntityPlayerMP player = ctx.getServerHandler().player;

        AuthorizedServer auth = (AuthorizedServer) Vision.getInstance().getAuthorization();

        auth.userBuyItems(new BuyItemRequest(EntityPlayer.getUUID(player.getGameProfile()).toString(), requestItems));

        player.sendMessage(new TextComponentString(" " + player.getServerWorld().isRemote + " " + Arrays.toString(requestItems)));

        return null;
    }
}
