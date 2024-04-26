package com.cifrazia.vision.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class BackgroundRenderer {

    private ResourceLocation backgroundTexture;

    public BackgroundRenderer(ResourceLocation backgroundTexture) {
        this.backgroundTexture = backgroundTexture;

        loadBackgroundTexture();
    }

    private void loadBackgroundTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(backgroundTexture);
    }

    private static void renderBackground() {
        /*if (backgroundTexture != null) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            backgroundTexture.bind(); // Bind the texture

            GL11.glBegin(GL11.GL_QUADS);

            // Draw a textured quad
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(0, 0);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(Display.getWidth(), 0);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(Display.getWidth(), Display.getHeight());
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(0, Display.getHeight());

            GL11.glEnd();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }*/
    }
}
