package com.cifrazia.vision.core.network.handlers.server;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedServer;
import com.cifrazia.vision.connection.data.request.WalletUserRequest;
import com.cifrazia.vision.core.network.packets.client.ClientBalanceResponsePacket;
import com.cifrazia.vision.core.network.packets.server.ServerBalanceRequestPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ServerBalanceHandler implements IMessageHandler<ServerBalanceRequestPacket, IMessage> {

    @Override
    public IMessage onMessage(ServerBalanceRequestPacket message, MessageContext ctx) {
        if(!Objects.requireNonNull(ctx.getServerHandler().player.getServer()).isDedicatedServer())return null;
        EntityPlayerMP player = ctx.getServerHandler().player;
        String playerUUID = EntityPlayer.getUUID(player.getGameProfile()).toString();
        AuthorizedServer auth = (AuthorizedServer) Vision.getInstance().getAuthorization();

        CompletableFuture.supplyAsync(() -> {
            long[] arr = new long[2];
            arr[0] = auth.getUserWallet(new WalletUserRequest("ruby", playerUUID)).getBalance();
            arr[1] = auth.getUserWallet(new WalletUserRequest("gold", playerUUID)).getBalance();
            return arr;
        }).thenAccept(arr -> {
            Vision.getInstance().getNetwork().sendTo(new ClientBalanceResponsePacket(arr[0], arr[1]), player);
        });
        return null;
    }
}
