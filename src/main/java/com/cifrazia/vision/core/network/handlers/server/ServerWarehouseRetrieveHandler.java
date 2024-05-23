package com.cifrazia.vision.core.network.handlers.server;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedServer;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItem;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseItemHolder;
import com.cifrazia.vision.connection.data.element.warehouse.WarehouseUserRequest;
import com.cifrazia.vision.core.network.packets.client.ClientWarehouseResultPacket;
import com.cifrazia.vision.core.network.packets.server.ServerWarehouseRetrievePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ServerWarehouseRetrieveHandler implements IMessageHandler<ServerWarehouseRetrievePacket, IMessage> {
    @Override
    public IMessage onMessage(ServerWarehouseRetrievePacket packet, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        CompletableFuture.supplyAsync(() -> {
                    int[] items = packet.getItems();
                    AuthorizedServer auth = (AuthorizedServer) Vision.getInstance().getAuthorization();

                    List<WarehouseItemHolder> warehouseItems = new ArrayList<>();

                    List<WarehouseItem> warehouseItemList = auth.userWarehouseRetrieve(new WarehouseUserRequest(EntityPlayer.getUUID(player.getGameProfile()).toString(), items));

                    for (WarehouseItem warehouseItem : warehouseItemList) {
                        warehouseItems.add(new WarehouseItemHolder(warehouseItem));
                    }

                    return warehouseItems;
                }, Vision.getInstance().getExecutorService())
                .thenAccept(data -> {
                    Objects.requireNonNull(ctx.getServerHandler().player.getServer()).addScheduledTask(() -> {
                        int slot = player.inventory.getFirstEmptyStack();

                        if (slot != -1) {
                            player.inventory.addItemStackToInventory(data.get(0).getItemStack());
                        }

                        Vision.getInstance().getNetwork().sendTo(new ClientWarehouseResultPacket(slot), player);
                    });
                });

        return null;
    }
}
