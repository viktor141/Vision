package com.cifrazia.custommainmenu.menu;

import com.cifrazia.custommainmenu.CustomMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class PressMenu extends GuiScreen {

    private ResourceLocation background = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/server_enter.png");

    @Override
    public void initGui() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(background);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        drawModalRectWithCustomSizedTexture(
                0, 0,
                0, 0,
                mc.displayWidth, mc.displayHeight,
                width, height);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton == 0){
            mc.displayGuiScreen(new MainMenu());
        }
    }
}
