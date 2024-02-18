package com.cifrazia.custommainmenu.menu;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.gui.Donation;
import com.cifrazia.custommainmenu.core.ui.gui.NavigationBar;
import com.cifrazia.custommainmenu.core.ui.gui.Shop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GeneralBusinessMenu extends Gui {

    private int panelStartX = 0;
    private int panelStartY = 0;
    private int panelEndX = 0;
    private int panelEndY = 100 >> 1;
    private NavigationBar navigationBar;
    private Shop shop;
    private Donation donation;
    private ResourceLocation background = new ResourceLocation(CustomMainMenu.MOD_ID, "textures/gui/buisness_menu_bg.png");


    @Override
    public void initGui() {
        super.initGui();

        shop = new Shop(mc, 0, panelEndY);
        donation = new Donation(mc, 0, panelEndY);

        navigationBar = new NavigationBar(mc, this, shop, donation);
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

        drawRect(panelStartX, panelStartY, panelEndX, panelEndY, 1711276032);

        super.drawScreen(mouseX, mouseY, partialTicks);
        navigationBar.drawScreen(mouseX, mouseY, partialTicks);
    }


    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        panelEndX = width;
        navigationBar.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        navigationBar.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }
}
