package com.cifrazia.vision.menu;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.ModalScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;

import static com.cifrazia.vision.core.ui.util.Color.EIGHT_PRESENT;

public class ModalWindow extends Gui {

    private final Gui parentGui;
    private final ModalScreen modalScreen;


    public ModalWindow(ModalScreen modalScreen) {
        this.modalScreen = modalScreen;
        this.parentGui = modalScreen.getParentGui();
    }

    @Override
    public void initGui() {
        super.initGui();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (parentGui != null) parentGui.drawScreen(0, 0, partialTicks);

        GlStateManager.disableDepth();

        drawRect(0, 0, width, height, EIGHT_PRESENT.getFullColor());

        modalScreen.drawScreen(mouseX, mouseY, partialTicks);

        GlStateManager.enableDepth();
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        modalScreen.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        modalScreen.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        modalScreen.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        modalScreen.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        modalScreen.handleMouseInput();
    }
}
