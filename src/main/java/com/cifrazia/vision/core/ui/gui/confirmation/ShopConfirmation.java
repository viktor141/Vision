package com.cifrazia.vision.core.ui.gui.confirmation;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.PanelScreen;
import com.cifrazia.vision.core.misc.MessageParser;
import com.cifrazia.vision.core.network.packets.BuyItemPacket;
import com.cifrazia.vision.core.ui.buttons.modal.BuyConfirmButton;
import com.cifrazia.vision.core.ui.buttons.modal.ModalGrayButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;

public class ShopConfirmation extends PanelScreen {

    private final BuyConfirmButton agree;
    private final ModalGrayButton cancel;
    private final ShopTradeOffer offer;
    private final Render render;

    public ShopConfirmation(Minecraft mc, Gui parentGui, ShopTradeOffer offer) {
        super(new ConfirmationPanel(mc));
        this.mc = mc;
        this.offer = offer;
        this.parentGui = parentGui;

        render = new Render(mc);
        render.setScale(48F);

        agree = addButton(new BuyConfirmButton(this, 0, 0, offer));
        cancel = addButton(new ModalGrayButton(this, 0, 0, "Cancel", -1));

        cancel.setEvent(() -> mc.displayGuiScreen(parentGui));
        agree.setEvent(() -> {
            ShopItem shopItem = offer.getShopItem();
            RequestItem[] requestItems = new RequestItem[]{new RequestItem(shopItem.getId(), 1)};
            Vision.getInstance().getNetwork().sendToServer(new BuyItemPacket(MessageParser.getJsonObject(requestItems), offer.getItemStack().getDisplayName()));
            cancel.onClick();
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(mc.fontRenderer, "Are you sure?", screenStartX + (230 >> 1), screenStartY + (50 >> 1), Color.WHITE_100.getFullColor());

        render.renderEffectsItem(offer.getItemStack(), screenStartX + (220 >> 1), screenStartY + (136 >> 1));
    }

    @Override
    public void setResolution(int width, int height) {
        screenWidth = 460 >> 1;
        screenHeight = 318 >> 1;

        super.setResolution(width, height);

        agree.updateCords(screenStartX + (234 >> 1), screenStartY + (240 >> 1));
        cancel.updateCords(screenStartX + (24 >> 1), screenStartY + (240 >> 1));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseX < screenStartX || mouseX > screenEndX || mouseY < screenStartY || mouseY > screenEndY) {
            mc.displayGuiScreen(parentGui);
        }
    }
}
