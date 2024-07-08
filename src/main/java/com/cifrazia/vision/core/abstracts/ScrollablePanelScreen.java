package com.cifrazia.vision.core.abstracts;

import com.cifrazia.vision.core.ui.util.ScrollBar;
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;

import java.io.IOException;

public abstract class ScrollablePanelScreen extends PanelScreen {

    protected final ScrollBar scrollBar;

    public ScrollablePanelScreen(ConfirmationPanel confirmationPanel, int barBackgroundWidth, int barGap) {
        super(confirmationPanel);
        scrollBar = new ScrollBar(barBackgroundWidth, barGap);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        scrollBar.drawScrollBar();
    }

    @Override
    public void setResolution(int width, int height) {
        super.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        scrollBar.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        scrollBar.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        scrollBar.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        scrollBar.handleMouseInput();
    }
}
