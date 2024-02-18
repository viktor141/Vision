package com.cifrazia.custommainmenu.core.ui.buttons.base;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public abstract class SquareButton extends Button {

    protected int borderX;
    protected int borderY;
    protected int uUnHovered;
    protected int uHovered;
    protected int v;
    protected int borderTextureSize;

    public SquareButton(Gui parentGUI, int x, int y, int color) {
        super(parentGUI, x, y, 54 >> 1, 54 >> 1, "", color);
        borderX = x - 3;
        borderY = y - 3;
        uUnHovered = 62 >> 1;
        uHovered = 136 >> 1;
        v = 52 >> 1;
        borderTextureSize = 66 >> 1;
        texturePosY = 128 >> 1;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        drawModalRectWithCustomSizedTexture(
                x, y,
                texturePosX, texturePosY,
                width, height,
                sizeOfTextureKit, sizeOfTextureKit);
    }

    @Override
    protected void hoverStateRender() {
        if (hovered) {
            drawModalRectWithCustomSizedTexture(
                    borderX, borderY,
                    uHovered, v,
                    borderTextureSize, borderTextureSize,
                    sizeOfTextureKit, sizeOfTextureKit);
        } else {
            drawModalRectWithCustomSizedTexture(
                    borderX, borderY,
                    uUnHovered, v,
                    borderTextureSize, borderTextureSize,
                    sizeOfTextureKit, sizeOfTextureKit);
        }
    }
}
