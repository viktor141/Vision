package com.cifrazia.vision.core.ui.util.panel;

import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.Element;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class InformationPanel implements Panel {

    private final Minecraft mc;
    private final ScaledResolution scaledResolution;
    private final int gap = 10 >> 1;
    private final String name;
    private PanelFrame panelFrame;
    private List<Element> elements = new ArrayList<>();
    private boolean visible;
    private int x;
    private int y;
    private float boxScale;
    private float textScale;
    private int scaledSize;
    private int contentGapY;
    private int contentGapX;

    public InformationPanel(Minecraft mc, String name, ScaledResolution scaledResolution) {
        this.mc = mc;
        this.name = name;
        this.scaledResolution = scaledResolution;
        panelFrame = new PanelFrame(Color.SIX_PRESENT, Color.WHITE_20);

    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
        panelUpdate();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (!visible) return;


        panelFrame.draw(mouseX, mouseY);


        mc.fontRenderer.drawString(name, x + (20 >> 1), y - (30 >> 1), -1);

        for (Element element : elements) {
            element.drawElement(mouseX, mouseY);
        }
    }

    @Override
    public void setUp(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        panelFrame.setUp(x, y, width);
        panelUpdate();
    }

    public void setUp(int x, int y, int width) {
        setUp(x, y, width, 0);
    }

    public void updateY(int y) {
        this.y = y;

        panelFrame.updateCoords(x, y);
        updateElementsY();
    }

    public void panelUpdate() {
        if (elements.isEmpty()) {
            visible = false;
            return;
        }

        visible = true;

        int boxWidth = elements.get(0).getBoxWidth();

        if (scaledResolution.getScaleFactor() <= 2) {
            boxScale = 1.5f;
            textScale = 1.0f;
            scaledSize = MathHelper.ceil(boxWidth * 1.6f);
        } else {
            boxScale = 1.0f;
            textScale = 0.5f;
            scaledSize = MathHelper.ceil(boxWidth * 0.8f);
        }

        contentGapY = (int) (scaledResolution.getScaledHeight() * 0.035f);
        //contentGapY += contentGapY % 2;
        contentGapX = (int) (scaledResolution.getScaledWidth() * 0.016f);
        int contentHeight = elements.size() * (scaledSize + gap);

        panelFrame.setHeight(contentHeight + contentGapY);

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            element.setUp(x + (contentGapX >> 1), 0, getWidth(), boxScale, textScale, scaledSize);
        }

    }

    private void updateElementsY() {
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            element.updateY(y + ((contentGapY >> 1) - (panelFrame.getGap() >> 1)) + (i * (scaledSize + gap)));
        }
    }


    public int getWidth() {
        return panelFrame.getWidth();
    }

    public int getHeight() {
        return panelFrame.getHeight();
    }
}
