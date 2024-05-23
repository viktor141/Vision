package com.cifrazia.vision.core.abstracts;

import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;

public abstract class PanelScreen extends ModalScreen {
    protected final ConfirmationPanel confirmationPanel;


    public PanelScreen(ConfirmationPanel confirmationPanel){
        this.confirmationPanel = confirmationPanel;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        confirmationPanel.draw(mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void setResolution(int width, int height) {
        screenStartX = (width >> 1) - (screenWidth >> 1);
        screenStartY = (height >> 1) - (screenHeight >> 1);

        screenEndX = screenStartX + screenWidth;
        screenEndY = screenStartY + screenHeight;

        confirmationPanel.setUp(screenStartX, screenStartY, screenWidth, screenHeight);
    }
}
