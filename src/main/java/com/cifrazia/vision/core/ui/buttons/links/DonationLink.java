package com.cifrazia.vision.core.ui.buttons.links;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.Button;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class DonationLink extends Button {
    protected int borderX;
    protected int borderY;
    protected int uUnHovered;
    protected int uHovered;
    protected int v;
    protected int borderTextureSize;

    public DonationLink(Gui parentGUI, int x, int y) {
        super(parentGUI, x, y, 48 >> 1, 48 >> 1, "", -1);

        buttonTextureKit = Vision.IN_GAME_KIT;
        uUnHovered = 152 >> 1;
        uHovered = 222 >> 1;
        v = 92 >> 1;
        borderTextureSize = 56 >> 1;
        texturePosX = 0;
        texturePosY = 162 >> 1;

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

    protected void borderSet(){
        borderX = x - 2;
        borderY = y - 2;
    }

    @Override
    public void updateCords(int x, int y) {
        super.updateCords(x, y);

        borderSet();
    }
}
