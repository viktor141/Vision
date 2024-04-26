package com.cifrazia.vision.core.ui.util.draw;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ScissoredDraw {
    private final ScaledResolution scaledResolution;
    private final Minecraft mc;
    private final int screenStartX;
    private final int screenStartY;
    private final int screenWidth;
    private final int screenHeight;
    private int scissorY;
    private int scissorHeight;

    public ScissoredDraw(Minecraft mc, int screenStartX, int screenStartY, int screenWidth, int screenHeight) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        scaledResolution = new ScaledResolution(mc);


        setup();
    }

    private void setup(){
        scissorHeight = screenHeight * scaledResolution.getScaleFactor();
        scissorY = mc.displayHeight - (screenStartY * scaledResolution.getScaleFactor()) - scissorHeight;
    }

    public void draw(Runnable runnable) {
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(screenStartX * scaledResolution.getScaleFactor(), scissorY, screenWidth * scaledResolution.getScaleFactor(), scissorHeight);

        runnable.run();

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
}
