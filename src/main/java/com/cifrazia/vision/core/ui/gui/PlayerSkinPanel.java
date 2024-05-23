package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class PlayerSkinPanel extends Screen {

    public PlayerSkinPanel(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.renderEngine.bindTexture(Vision.IN_GAME_KIT);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                screenStartX, screenStartY,
                276 >> 1, 30 >> 1,
                210 >> 1, 54 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();
    }

    @Override
    public void setResolution(int width, int height) {
        screenStartX = 60 >> 1;//59
        screenStartY = 294 >> 1;
    }
}
