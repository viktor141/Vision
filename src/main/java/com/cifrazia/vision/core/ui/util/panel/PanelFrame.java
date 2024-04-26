package com.cifrazia.vision.core.ui.util.panel;

import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import net.minecraft.client.gui.Gui;

public class PanelFrame {
    private final Color panelBackground;
    private final Color panelLine;
    private int x;
    private int y;
    private int height;
    private int width;
    private int gap;
    private int actualWidth;
    private int actualHeight;

    public PanelFrame(Color panelBackground, Color panelLine) {
        this.panelBackground = panelBackground;
        this.panelLine = panelLine;
    }

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, panelBackground.getFullColor());

        drawVerticalLine(x, y, gap);
        drawVerticalLine(x + width, y, -gap);

        drawHorizontalLine(x, y, gap);
        drawHorizontalLine(x, y + height, -gap);
    }

    protected void drawVerticalLine(int x, int y, int side) {
        Draw.drawRect(x - side, y, x, y + height, panelBackground.getFullColor());
        Draw.drawRect(x, y + gap, x + side, y + height - gap, panelLine.getFullColor());
    }

    protected void drawHorizontalLine(int x, int y, int side) {
        Draw.drawRect(x, y - side, x + width, y, panelBackground.getFullColor());
        Draw.drawRect(x + gap, y, x + width - gap, y + side, panelLine.getFullColor());
    }

    public void setUp(int x, int y, int width) {
        this.setUp(x, y, width, width / 70);
    }

    public void setUp(int x, int y, int width, int gap) {
        this.gap = gap;
        this.x = x + gap;
        this.y = y + gap;
        this.width = width - (gap << 1);
        actualWidth = width;
    }

    public int getWidth() {
        return width;
    }

    public int getGap() {
        return gap;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height - (gap << 1);
        actualHeight = height;
    }

}

/*public void drawPanel(int mouseX, int mouseY) {
    int y = panelY;
    drawUpCap(panelX, y);

    y += panel.getHeight();
    drawBody(panelX, y);

    y += height;
    drawUnderCap(panelX, y);
}*/

/*private void drawUpCap(int x, int y) {
        mc.getTextureManager().bindTexture(Vision.DONATION_KIT);
        GlStateManager.enableBlend();
        GlStateManager.color(Color.WHITE_100.getR(), Color.WHITE_100.getG(), Color.WHITE_100.getB(), Color.WHITE_100.getA());
        Draw.drawModalSquareWithCustomSizedTexture(
                x, y,
                panel.getX(), panel.getY(),
                panel.getWidth(), panel.getHeight(),
                panel.getSize());
        GlStateManager.disableBlend();
    }

    private void drawUnderCap(int x, int y) {
        mc.getTextureManager().bindTexture(Vision.DONATION_KIT);

        GlStateManager.color(Color.WHITE_100.getR(), Color.WHITE_100.getG(), Color.WHITE_100.getB(), Color.WHITE_100.getA());
        GlStateManager.enableBlend();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180, 0, 0, 1);
        Draw.drawModalSquareWithCustomSizedTexture(
                -(x + panel.getWidth()), -(y + panel.getHeight()),
                panel.getX(), panel.getY(),
                panel.getWidth(), panel.getHeight(),
                panel.getSize());
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
    }*/

/*private void drawBody(int x, int y) {
        Gui.drawRect(x, y, x + panel.getWidth(), y + height, PANEL_BACKGROUND.getFullColor());

        Gui.drawRect(x + panelGap, y, x + (panelGap << 1), y + height, PANEL_LINE.getFullColor());
        Gui.drawRect(x + panel.getWidth() - (panelGap << 1), y, x + panel.getWidth() - panelGap, y + height, PANEL_LINE.getFullColor());
    }*/