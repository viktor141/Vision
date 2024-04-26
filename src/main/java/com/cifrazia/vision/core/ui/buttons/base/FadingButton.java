package com.cifrazia.vision.core.ui.buttons.base;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class FadingButton extends ActiveButton {


    public FadingButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(parentGUI, buttons, x, y, widthIn, heightIn, buttonText, -1);

    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!pressed) {
            renderColor = Color.HALF;
        } else {
            renderColor = Color.WHITE_100;
        }
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x, y,
                texturePosX, texturePosY,
                width, height,
                sizeOfTextureKit, sizeOfTextureKit);
        GlStateManager.disableBlend();
    }
}
