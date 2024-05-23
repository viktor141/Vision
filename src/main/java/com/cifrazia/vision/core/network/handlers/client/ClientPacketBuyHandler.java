package com.cifrazia.vision.core.network.handlers.client;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.misc.MessageParser;
import com.cifrazia.vision.core.network.packets.BuyItemPacket;
import com.cifrazia.vision.core.ui.gui.outcome.success.BuyItemSuccess;
import com.cifrazia.vision.menu.ModalWindow;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientPacketBuyHandler implements IMessageHandler<BuyItemPacket, IMessage> {

    @Override
    public IMessage onMessage(BuyItemPacket packet, MessageContext ctx) {
        RequestItem[] requestItems = MessageParser.getJsonObject(packet.getJsonItems());
        Minecraft mc = Minecraft.getMinecraft();

        if (requestItems.length > 0) {
            mc.displayGuiScreen(new ModalWindow(new BuyItemSuccess(mc, (Gui) mc.currentScreen, packet.getName())));
            ((AuthorizedClient) Vision.getInstance().getAuthorization()).getWarehouseData().forceUpdate();
        }

        return null;
    }
}
