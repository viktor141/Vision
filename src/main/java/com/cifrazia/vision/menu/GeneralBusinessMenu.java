package com.cifrazia.vision.menu;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.NavigationBarButton;
import com.cifrazia.vision.core.ui.gui.*;
import com.cifrazia.vision.core.ui.gui.page.Donation;
import com.cifrazia.vision.core.ui.gui.page.Shop;
import com.cifrazia.vision.core.ui.gui.page.Warehouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GeneralBusinessMenu extends Gui {

    private BankPanel bankPanel;
    private int panelStartX = 0;
    private int panelStartY = 0;
    private int panelEndX = 0;
    private int panelEndY = 100 >> 1;
    private NavigationBar navigationBar;
    private Shop shop;
    private Donation donation;
    private Warehouse warehouse;
    private ResourceLocation background = new ResourceLocation(Vision.MOD_ID, "textures/gui/buisness_menu_bg.png");
    private final String iniScreen;
    private boolean clickEmulated;

    public GeneralBusinessMenu(String iniScreen) {
        this.iniScreen = iniScreen;
        this.mc = Minecraft.getMinecraft();

        shop = new Shop(mc, this, 0, panelEndY);
        donation = new Donation(mc, this, 0, panelEndY);
        warehouse = new Warehouse(mc, 110 >> 1, panelEndY);

        navigationBar = new NavigationBar(mc, this, shop, donation, warehouse);
        bankPanel = new BankPanel(mc, true);
    }

    private void emulateClick() {
        NavigationBarButton barButton = navigationBar.getButton(iniScreen);
        barButton.onClick();
        clickEmulated = true;
    }

    @Override
    public void initGui() {
        super.initGui();

        if (!clickEmulated) emulateClick();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();


        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, mc.displayWidth, mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
*/


        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(
                0, 0,
                0, 0,
                mc.displayWidth, mc.displayHeight,
                width, height);

        drawRect(panelStartX, panelStartY, panelEndX, panelEndY, 1711276032);

        bankPanel.drawScreen(mouseX, mouseY, partialTicks);
        navigationBar.drawScreen(mouseX, mouseY, partialTicks);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        panelEndX = width;
        navigationBar.setResolution(width, height);
        bankPanel.setUp(width - ((22 >> 1) + bankPanel.width), 26 >> 1);

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        navigationBar.mouseClicked(mouseX, mouseY, mouseButton);
        bankPanel.mouseClicked(mouseX, mouseY, mouseButton);
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

    public Shop getShop() {
        return shop;
    }

    public Donation getDonation() {
        return donation;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
