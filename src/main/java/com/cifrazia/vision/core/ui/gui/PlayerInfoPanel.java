package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.Vision.SIZE_OF_TEXTURE_KIT;

public class PlayerInfoPanel extends Gui {
    private final boolean reverse;
    private int x;
    private int y;

    public PlayerInfoPanel(Minecraft mc, boolean reverse) {
        this.mc = mc;
        this.reverse = reverse;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.renderEngine.bindTexture(Vision.DONATION_KIT);

        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(
                x - (4 >> 1), y - (4 >> 1),
                354 >> 1, 0,
                56 >> 1, 56 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);

        drawModalRectWithCustomSizedTexture(
                x, y,
                436 >> 1, 0,
                48 >> 1, 48 >> 1,
                SIZE_OF_TEXTURE_KIT, SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();

        drawString(mc.fontRenderer, "Player", x + (64 >> 1), y + (6 >> 1), -1);
        drawString(mc.fontRenderer, "Never Ever", x + (64 >> 1), y + (30 >> 1), -1);
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
