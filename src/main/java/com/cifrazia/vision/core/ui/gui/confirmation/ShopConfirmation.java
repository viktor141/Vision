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
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;
import com.cifrazia.vision.core.ui.util.render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ShopConfirmation extends PanelScreen {

    private final BuyConfirmButton agree;
    private final ModalGrayButton cancel;
    private final ShopTradeOffer offer;
    private final Render render;
    private final int arrowSize = 32;
    private final ItemStack item;
    private List<String> description;
    private int count;
    private int itemX;
    private int itemY;
    private int arrowY;
    private int arrowXLeft;
    private int arrowXRight;


    public ShopConfirmation(Minecraft mc, Gui parentGui, ShopTradeOffer offer) {
        super(new ConfirmationPanel(mc));
        this.mc = mc;
        this.offer = offer;
        this.parentGui = parentGui;

        fontRenderer = mc.fontRenderer;
        item = offer.getItemStack().copy();

        render = new Render(mc);
        render.setScale(48F);

        agree = addButton(new BuyConfirmButton(this, 0, 0, offer));
        cancel = addButton(new ModalGrayButton(this, 0, 0, "Cancel", -1));

        count = 1;

        cancel.setEvent(this::onParentScreen);
        agree.setEvent(() -> {
            ShopItem shopItem = offer.getShopItem();
            RequestItem[] requestItems = new RequestItem[]{new RequestItem(shopItem.getId(), count)};
            Vision.getInstance().getNetwork().sendToServer(new BuyItemPacket(MessageParser.getJsonObject(requestItems), offer.getItemStack().getDisplayName()));
            cancel.onClick();
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int gap = 2;

        int fixY = ((mc.fontRenderer.FONT_HEIGHT + gap) * (description.size() - 1)) >> 1;

        for (int i = 0; i < description.size(); i++) {
            drawCenteredString(
                    mc.fontRenderer,
                    description.get(i),
                    screenStartX + (230 >> 1),
                    screenStartY + (50 >> 1) - fixY + ((mc.fontRenderer.FONT_HEIGHT + gap) * i),
                    -1);
        }

        drawArrow(arrowXLeft, arrowY, false);
        drawArrow(arrowXRight, arrowY, true);

        render.renderEffectsItem(item, itemX, itemY);

        if (render.isMouseOnItem(mouseX, mouseY, itemX, itemY, item))
            drawItemTooltip(mouseX, mouseY, item);

        if (isOnLeftArrow(mouseX, mouseY))
            drawHoveringText("-" + offer.getItemStack().getCount(), mouseX, mouseY);

        if (isOnRightArrow(mouseX, mouseY))
            drawHoveringText("+" + offer.getItemStack().getCount(), mouseX, mouseY);
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;

        screenWidth = 460 >> 1;
        screenHeight = 318 >> 1;

        super.setResolution(width, height);

        agree.updateCords(screenStartX + (234 >> 1), screenStartY + (240 >> 1));
        cancel.updateCords(screenStartX + (24 >> 1), screenStartY + (240 >> 1));

        description = mc.fontRenderer.listFormattedStringToWidth(
                "Are you sure, that you want to buy a " + offer.getItemStack().getDisplayName() + "?",
                screenWidth - 20);

        itemX = screenStartX + render.getRenderGap() + (screenWidth >> 1) - (((int) render.getScale()) >> 1);
        itemY = screenStartY + (screenHeight >> 1) - render.getRenderGap() + 10;

        arrowY = screenStartY + (screenHeight >> 1) - (arrowSize >> 1);
        arrowXLeft = screenStartX + (screenWidth >> 3);
        arrowXRight = (screenEndX - arrowSize) - (screenWidth >> 3);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseX < screenStartX || mouseX > screenEndX || mouseY < screenStartY || mouseY > screenEndY) {
            onParentScreen();
            return;
        }

        if (mouseButton != 0) return;

        if (isOnRightArrow(mouseX, mouseY)) {
            countUpdate(1);
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }

        if (isOnLeftArrow(mouseX, mouseY)) {
            countUpdate(-1);
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    private boolean isOnLeftArrow(int mouseX, int mouseY) {
        return mouseX > arrowXLeft && mouseX < arrowXLeft + arrowSize && mouseY > arrowY && mouseY < arrowY + arrowSize;
    }

    private boolean isOnRightArrow(int mouseX, int mouseY) {
        return mouseX > arrowXRight && mouseX < arrowXRight + arrowSize && mouseY > arrowY && mouseY < arrowY + arrowSize;
    }

    private void drawArrow(int x, int y, boolean reverted) {
        mc.getTextureManager().bindTexture(Vision.MODAL_KIT);
        drawScaledCustomSizeModalRect(
                x, y,
                reverted ? 256 : 0, 256,
                256, 256,
                arrowSize, arrowSize,
                512, 512);
    }

    private void countUpdate(int value) {
        count += value;
        if (count < 1) count = 1;

        item.setCount(offer.getItemStack().getCount() * count);
        offer.setCount(count);
    }

    private void onParentScreen() {
        mc.displayGuiScreen(parentGui);
        offer.setCount(1);
    }
}
