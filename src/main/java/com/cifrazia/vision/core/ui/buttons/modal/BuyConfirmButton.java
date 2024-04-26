package com.cifrazia.vision.core.ui.buttons.modal;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.shop.ShopTradeOffer;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class BuyConfirmButton extends ModalGreenButton {

    private final ShopTradeOffer offer;

    public BuyConfirmButton(Gui parentGUI, int x, int y, ShopTradeOffer offer) {
        super(parentGUI, x, y, "", -1);
        this.offer = offer;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        Draw.drawBuyButtonWithPrice(mc, x, y, width, height, offer, color);
    }

    /*drawCenteredString(mc.fontRenderer, "Buy ", x + (width >> 1) - (width >> 2), y + (18 >> 1), color);
        String price = String.valueOf(offer.getShopItem().getPrice());

        int stringWidth = mc.fontRenderer.getStringWidth(price) + (16 >> 1);
        int priceX = x + (width >> 2) + (width >> 1) - (stringWidth >> 1) - 12;
        int priceY = y + (18 >> 1);

        drawRect(priceX, priceY - 3, priceX + stringWidth + (12 >> 1), priceY + (22 >> 1), Color.SIX_PRESENT.getFullColor());
        drawCenteredString(mc.fontRenderer, price, priceX + (stringWidth >> 1), priceY, color);

        mc.getTextureManager().bindTexture(Vision.IN_GAME_KIT);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                priceX + stringWidth, priceY - 2,
                34 >> 1, 0,
                24 >> 1, 24 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();*/
}
