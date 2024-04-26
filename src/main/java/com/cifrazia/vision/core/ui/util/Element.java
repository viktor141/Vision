package com.cifrazia.vision.core.ui.util;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import com.cifrazia.vision.core.ui.util.texture.TextureData;
import com.cifrazia.vision.core.ui.util.texture.TextureSizeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class Element {
    private final Minecraft mc;
    private final String text;
    private final String underText;
    private final TextureSizeData size = new TextureSizeData(Vision.SIZE_OF_TEXTURE_KIT);
    private final TextureData box;
    private int x;
    private int y;

    public Element(Minecraft mc, String text, String underText) {
        this.mc = mc;
        this.text = text;
        this.underText = underText;

        box = new TextureData(size, 210 >> 1, 0, 48 >> 1, 48 >> 1);
    }

    public void drawElement(int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(Vision.DONATION_KIT);
        float scale = 1.6f;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        Draw.drawModalSquareWithCustomSizedTexture(
                (int) (x / scale), (int) (y / scale),
                box.getX(), box.getY(),
                box.getWidth(), box.getHeight(),
                box.getSize());

        mc.fontRenderer.drawString(text, (int) ((x + getSize() + (10 >> 1)) / scale), (int) ((y / scale) + (8 >> 1)), -1);
        GlStateManager.popMatrix();

        float scale2 = 1.2f;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale2, scale2, scale2);
        mc.fontRenderer.drawString(underText, (int) ((x + getSize() + (10 >> 1)) / scale2), (int) (y/scale2 + ((28 >> 1) * scale)), -1);
        GlStateManager.popMatrix();
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSize() {
        return (int) (box.getWidth() * 1.6);
    }
}
