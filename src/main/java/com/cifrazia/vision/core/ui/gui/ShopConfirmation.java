package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.shop.RequestItem;
import com.cifrazia.vision.connection.data.element.shop.ShopItem;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.misc.MessageParser;
import com.cifrazia.vision.core.network.ServerBuyItemPacket;
import com.cifrazia.vision.core.ui.buttons.modal.BuyConfirmButton;
import com.cifrazia.vision.core.ui.buttons.modal.ModalGrayButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;

public class ShopConfirmation extends Screen {

    private final BuyConfirmButton agree;
    private final ModalGrayButton cancel;
    private final ShopTradeOffer offer;
    private final Render render;
    private final Gui parentGui;
    private ConfirmationPanel confirmationPanel;


    public ShopConfirmation(Minecraft mc, Gui parentGui, ShopTradeOffer offer) {
        this.mc = mc;
        this.offer = offer;
        this.parentGui = parentGui;

        confirmationPanel = new ConfirmationPanel(mc);
        render = new Render(mc);
        render.setScale(48F);

        agree = addButton(new BuyConfirmButton(this, 0, 0, offer));
        cancel = addButton(new ModalGrayButton(this, 0, 0, "Cancel", -1));

        cancel.setEvent(() -> mc.displayGuiScreen(parentGui));
        agree.setEvent(() ->{
            ShopItem shopItem = offer.getShopItem();
            RequestItem[] requestItems = new RequestItem[]{new RequestItem(shopItem.getId(), shopItem.getCount())};
            Vision.getInstance().getNetwork().sendToServer(new ServerBuyItemPacket(MessageParser.getJsonObject(requestItems)));
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        confirmationPanel.draw(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(mc.fontRenderer, "Are you sure?", screenStartX + (230 >> 1), screenStartY + (50 >> 1), Color.WHITE_100.getFullColor());

        render.renderEffectsItem(offer.getItemStack(), screenStartX + (220 >> 1), screenStartY + (136 >> 1));
    }

    @Override
    public void setResolution(int width, int height) {
        screenWidth = 460 >> 1;
        screenHeight = 318 >> 1;

        screenStartX = (width >> 1) - (screenWidth >> 1);
        screenStartY = (height >> 1) - (screenHeight >> 1);

        screenEndX = screenStartX + screenWidth;
        screenEndY = screenStartY + screenHeight;

        agree.updateCords(screenStartX + (234 >> 1), screenStartY + (240 >> 1));
        cancel.updateCords(screenStartX + (24 >> 1), screenStartY + (240 >> 1));

        confirmationPanel.setUp(screenStartX, screenStartY, screenWidth, screenHeight);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(mouseX < screenStartX || mouseX > screenEndX || mouseY < screenStartY || mouseY > screenEndY) {
            mc.displayGuiScreen(parentGui);
        }
    }
}
