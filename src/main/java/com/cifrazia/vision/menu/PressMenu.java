package com.cifrazia.vision.menu;

import com.cifrazia.vision.core.ui.util.texture.BackgroundTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class PressMenu extends GuiScreen {

    private final BackgroundTexture bg = new BackgroundTexture();
    private int x;
    private int y;
    private float bgTextureWidth;
    private float bgTextureHeight;

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        bg.draw();

        super.drawScreen(mouseX, mouseY, partialTicks);
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
