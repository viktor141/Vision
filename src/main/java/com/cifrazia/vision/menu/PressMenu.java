package com.cifrazia.vision.menu;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.ui.util.texture.BackgroundTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;

public class PressMenu extends GuiScreen {

    private final BackgroundTexture bg = new BackgroundTexture();
    private final float maxValue = 1.0F;
    private final float cycleLength = maxValue * 2;
    private final float increment = 0.03F;
    private float step = 0.0F;


    @Override
    public void initGui() {
        super.initGui();
    }

    private float getValue() {
        float value = step <= maxValue ? maxValue - step : step - maxValue;
        step = (step + increment) % cycleLength;
        return value;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        bg.draw();

        super.drawScreen(mouseX, mouseY, partialTicks);

        String press = "PRESS";
        String start = "TO START PLAYING";
        int x = (width >> 1) - (width >> 2) - (width >> 3), y = (height >> 1) - (height >> 2);
        int stringWidth = mc.fontRenderer.getStringWidth(press);
        int mouseHeight = 30 >> 1;

        drawString(mc.fontRenderer, press, x, y, -1);
        drawString(mc.fontRenderer, start, x + stringWidth + (52 >> 1), y, -1);

        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(Vision.SERVER_KIT);
        GlStateManager.color(1.0F, 1.0F, 1.0F, getValue());
        drawModalRectWithCustomSizedTexture(
                x + stringWidth + (16 >> 1), y - ((mouseHeight - mc.fontRenderer.FONT_HEIGHT) >> 1),
                274 >> 1, mouseHeight,
                20 >> 1, 30 >> 1,
                Vision.SIZE_OF_TEXTURE_KIT, Vision.SIZE_OF_TEXTURE_KIT);
        GlStateManager.disableBlend();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            mc.displayGuiScreen(new MainMenu());
        }

    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        bg.setResolution(mc, width, height);
    }
}
