package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.StaticButton;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class BuyItemsButton extends StaticButton {

    private final ShopTradeOffer offer;

    public BuyItemsButton(Gui parentGUI, int x, int y, ShopTradeOffer offer) {
        super(parentGUI, x, y, 164 >> 1, 38 >> 1, "", -1);
        this.offer = offer;

        buttonTextureKit = Vision.NAVIGATION_SHOP_KIT;

        texturePosX = 0;
        texturePosY = 264 >> 1;

        uHovered = 172 >> 1;
        v = 260 >> 1;
        borderTextureWidth = 168 >> 1;
        borderTextureHeight = 42 >> 1;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        Draw.drawBuyButtonWithPrice(mc, x, y, width, height, offer, color);
    }

    @Override
    protected void borderSet() {
        borderX = x - 1;
        borderY = y - 1;
    }
}
