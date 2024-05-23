package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.ActiveButton;
import com.cifrazia.vision.core.ui.buttons.base.ActiveTransparentButton;
import com.cifrazia.vision.core.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nonnull;
import java.util.List;

public class CategoryButton extends ActiveTransparentButton {

    private final int categoryId;

    public CategoryButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, String buttonText, int categoryId) {
        super(parentGUI, buttons, x, y, 182 >> 1, 46 >> 1, buttonText);
        this.categoryId = categoryId;

        buttonTextureKit = Vision.NAVIGATION_SHOP_KIT;

        texturePosX = 202 >> 1;
        texturePosY = 428 >> 1;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        mc.getTextureManager().bindTexture(buttonTextureKit);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x - (56 >> 1), y,
                texturePosX - (54 >> 1), texturePosY,
                50 >> 1, 40 >> 1,
                sizeOfTextureKit, sizeOfTextureKit);
        GlStateManager.disableBlend();

        drawCenteredString(mc.fontRenderer, "#" + categoryId, x - (30 >> 1), y + (14 >> 1),
                pressed ? Color.GREEN.getFullColor() : (hovered ? Color.WHITE_100.getFullColor() : Color.GRAY.getFullColor()));
    }
}
