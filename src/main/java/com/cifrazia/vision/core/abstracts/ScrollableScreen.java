package com.cifrazia.vision.core.abstracts;

import com.cifrazia.vision.core.ui.util.ScrollBar;

import java.io.IOException;

public abstract class ScrollableScreen extends Screen {

    protected final ScrollBar scrollBar;

    public ScrollableScreen(int barBackgroundWidth, int barGap) {
        scrollBar = new ScrollBar(barBackgroundWidth, barGap);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        scrollBar.drawScrollBar();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        scrollBar.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        scrollBar.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        scrollBar.mouseReleased(mouseX, mouseY, state);
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        scrollBar.handleMouseInput();
    }

}
