package com.cifrazia.vision.core.abstracts;

import java.io.IOException;

public abstract class Screen extends Gui {
    protected int screenStartX = 0;
    protected int screenStartY = 0;
    protected int screenWidth = 0;
    protected int screenHeight = 0;
    protected int screenEndX = 0;
    protected int screenEndY = 0;


    public abstract void setResolution(int width, int height);

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    @Override
    public void handleMouseInput() throws IOException {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
    }
}
