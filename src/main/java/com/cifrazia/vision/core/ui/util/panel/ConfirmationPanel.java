package com.cifrazia.vision.core.ui.util.panel;

import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.draw.Draw;
import net.minecraft.client.Minecraft;

public class ConfirmationPanel {
    private static final Color UPPER_START = new Color(242, 242, 242, 255);
    private static final Color UPPER_END = new Color(159, 159, 159, 255);

    private static final Color BOTTOM_START = new Color(95, 95, 95, 255);
    private static final Color BOTTOM_END = new Color(41, 41, 41, 255);

    private static final Color LEFT_START = new Color(235, 235, 235, 255);
    private static final Color LEFT_END = new Color(144, 144, 144, 255);

    private static final Color RIGHT_START = new Color(88, 88, 88, 255);
    private static final Color RIGHT_END = new Color(40, 40, 40, 255);

    private final PanelFrame panelFrame;
    private int x;
    private int y;

    public ConfirmationPanel(Minecraft mc) {
        panelFrame = new PanelFrame(Color.NINE_PRESENT, Color.EMPTY);
    }

    public void draw(int mouseX, int mouseY) {
        //Draw.drawRect(0, 0, 1000, 1000, -1);

        panelFrame.draw(mouseX, mouseY);

        //Upper Line
        Draw.drawGradientRectangle(
                (panelFrame.getGap() << 1) + x, panelFrame.getGap() + y,
                panelFrame.getWidth() + x, (panelFrame.getGap() << 1) + y,
                UPPER_START, UPPER_END, true);

        //Bottom Line
        Draw.drawGradientRectangle(
                (panelFrame.getGap() << 1) + x, panelFrame.getHeight() + y,
                panelFrame.getWidth() + x, panelFrame.getGap() + panelFrame.getHeight() + y,
                BOTTOM_START, BOTTOM_END, true);

        //Left Line
        Draw.drawGradientRectangle(
                panelFrame.getGap() + x, (panelFrame.getGap() << 1) + y,
                (panelFrame.getGap() << 1) + x, panelFrame.getHeight() + y,
                LEFT_START, LEFT_END, false);

        //Right Line
        Draw.drawGradientRectangle(
                panelFrame.getWidth() + x, (panelFrame.getGap() << 1) + y,
                +panelFrame.getWidth() + panelFrame.getGap() + x, panelFrame.getHeight() + y,
                RIGHT_START, RIGHT_END, false);
    }

    public void setUp(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        panelFrame.setUp(x, y, width, 6 >> 1);
        panelFrame.setHeight(height);
    }
}
