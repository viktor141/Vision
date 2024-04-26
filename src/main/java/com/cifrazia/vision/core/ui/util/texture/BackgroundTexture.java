package com.cifrazia.vision.core.ui.util.texture;

import com.cifrazia.vision.Vision;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture;

public class BackgroundTexture {

    private Minecraft mc;
    private int x;
    private int y;
    private int width;
    private int height;
    private float bgTextureWidth;
    private float bgTextureHeight;


    public void draw() {
        mc.getTextureManager().bindTexture(Vision.BACKGROUND);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        drawModalRectWithCustomSizedTexture(
                0,0,
                x, y,
                width, height,
                bgTextureWidth, bgTextureHeight);

    }


    public void setResolution(Minecraft mc,int width, int height){
        this.mc = mc;
        this.width = width;
        this.height = height;

        float coefficient = Math.max(width / 16f, height / 9f);

        bgTextureWidth = 16 * coefficient;
        bgTextureHeight = 9 * coefficient;

        x = (int) ((bgTextureWidth - width) / 2);
        y = (int) ((bgTextureHeight - height) / 2);
    }
}
