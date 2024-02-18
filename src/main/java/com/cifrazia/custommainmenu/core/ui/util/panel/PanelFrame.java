package com.cifrazia.custommainmenu.core.ui.util.panel;

import com.cifrazia.custommainmenu.core.ui.util.Color;
import net.minecraft.client.gui.Gui;

public class PanelFrame {
    private static final Color PANEL_BACKGROUND = new Color(0, 0, 0, 153);
    private static final Color PANEL_LINE = new Color(255, 255, 255, 51);
    private int x;
    private int y;
    private int height;
    private int width;
    private int gap;

    public void draw(int mouseX, int mouseY) {
        Gui.drawRect(x, y, x + width, y + height, PANEL_BACKGROUND.getFullColor());

        drawVerticalLine(x, y, gap);
        drawVerticalLine(x + width, y, -gap);

        drawHorizontalLine(x, y, gap);
        drawHorizontalLine(x, y + height, -gap);
    }

    private void drawVerticalLine(int x, int y, int side) {
        Gui.drawRect(x - side, y, x, y + height, PANEL_BACKGROUND.getFullColor());
        Gui.drawRect(x, y + gap, x + side, y + height - gap, PANEL_LINE.getFullColor());
    }

    private void drawHorizontalLine(int x, int y, int side) {
        Gui.drawRect(x, y - side, x + width, y, PANEL_BACKGROUND.getFullColor());
        Gui.drawRect(x + gap, y, x + width - gap, y + side, PANEL_LINE.getFullColor());
    }

    public void setUp(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;

        gap = width / 58;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
        mc.getTextureManager().bindTexture(CustomMainMenu.DONATION_KIT);
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
        mc.getTextureManager().bindTexture(CustomMainMenu.DONATION_KIT);

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