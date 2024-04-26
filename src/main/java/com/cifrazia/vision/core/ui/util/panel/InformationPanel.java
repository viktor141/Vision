package com.cifrazia.vision.core.ui.util.panel;

import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.Element;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class InformationPanel {

    private PanelFrame panelFrame;
    private List<Element> elements = new ArrayList<>();
    private int x;
    private int y;

    public InformationPanel(Minecraft mc) {
        panelFrame = new PanelFrame(Color.SIX_PRESENT, Color.WHITE_20);

        elements.add(new Element(mc, "/tpaccept", "Accept teleportation request"));
        elements.add(new Element(mc, "/tpaccept", "Принять запрос на телепортацию "));

    }

    public void draw(int mouseX, int mouseY) {
        panelFrame.draw(mouseX, mouseY);

        for (Element element : elements) {
            element.drawElement(mouseX, mouseY);
        }
    }

    public void setUp(int x, int y, int width) {
        this.x = x;
        this.y = y;
        if (elements.isEmpty()) return;

        panelFrame.setUp(x, y, width);
        int gap = 10 >> 1;
        int contentHeight = (elements.size() * (elements.get(0).getSize() + gap)) - gap;
        panelFrame.setHeight(contentHeight + (22 >> 1) + (20 >> 1));

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            element.setUp(x + (22 >> 1), y + (22 >> 1) + (i * (element.getSize() + gap)));
        }
    }

    public int getWidth() {
        return panelFrame.getWidth();
    }
}
