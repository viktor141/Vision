package com.cifrazia.vision.menu;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import static com.cifrazia.vision.core.ui.util.Color.EIGHT_PRESENT;

public class ModalWindow extends Gui {

    private final Gui parentGui;
    private final Screen modalWindow;


    public ModalWindow(Gui parentGui, Screen modalWindow) {
        this.parentGui = parentGui;
        this.modalWindow = modalWindow;
    }

    @Override
    public void initGui() {
        super.initGui();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        parentGui.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.disableDepth();

        drawRect(0, 0, width, height, EIGHT_PRESENT.getFullColor());

        drawString(mc.fontRenderer, "modal", 10, 10, -1);

        modalWindow.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.enableDepth();

    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        modalWindow.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        modalWindow.mouseClicked(mouseX, mouseY, mouseButton);
    }

}
