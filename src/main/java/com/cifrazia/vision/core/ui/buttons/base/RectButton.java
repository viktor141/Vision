package com.cifrazia.vision.core.ui.buttons.base;

import com.cifrazia.vision.core.abstracts.Gui;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public abstract class RectButton extends Button {

    protected int borderX;
    protected int borderY;
    protected int uUnHovered;
    protected int uHovered;
    protected int v;
    protected int borderTextureWidth;
    protected int borderTextureHeight;

    public RectButton(Gui parentGUI, int x, int y, int widthIn, int heightIn, String buttonText, int color) {
        super(parentGUI, x, y, widthIn, heightIn, buttonText, color);

        borderSet();
    }


    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        drawModalRectWithCustomSizedTexture(
                x, y,
                texturePosX, texturePosY,
                width, height,
                sizeOfTextureKit, sizeOfTextureKit);

        this.drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
    }

    @Override
    protected void hoverStateRender() {
        if (hovered) {
            drawModalRectWithCustomSizedTexture(
                    borderX, borderY,
                    uHovered, v,
                    borderTextureWidth, borderTextureHeight,
                    sizeOfTextureKit, sizeOfTextureKit);
        } else {
            drawModalRectWithCustomSizedTexture(
                    borderX, borderY,
                    uUnHovered, v,
                    borderTextureWidth, borderTextureHeight,
                    sizeOfTextureKit, sizeOfTextureKit);
        }
    }

    protected void borderSet() {
        borderX = x - 3;
        borderY = y - 3;
    }

    @Override
    public void updateCords(int x, int y) {
        super.updateCords(x, y);

        borderSet();
    }
}
