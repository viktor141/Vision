package com.cifrazia.custommainmenu.core.ui.util.panel;

import com.cifrazia.custommainmenu.core.ui.util.Element;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class InformationPanel {

    private PanelFrame panelFrame;
    private List<Element> elements = new ArrayList<>();
    private int x;
    private int y;

    public InformationPanel(Minecraft mc) {
        panelFrame = new PanelFrame();

        elements.add(new Element(mc, "/tpaccept"));

    }

    public void draw(int mouseX, int mouseY) {
        panelFrame.draw(mouseX, mouseY);

        for (Element element : elements) {
            element.drawElement(mouseX, mouseY);
        }
    }

    public void setUp(int x, int y) {
        this.x = x;
        this.y = y;

        panelFrame.setUp(x, y, 348 >> 1);
        int gap = 10 >> 1;
        panelFrame.setHeight((elements.size() * ((48 >> 1) + gap)) - gap + (22 >> 1) + (20 >> 1));

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            element.setUp(x + (22 >> 1), y + (22 >> 1) + (i * (element.getSize() + (10 >> 1))));
        }
    }

    public int getWidth() {
        return panelFrame.getWidth();
    }
}
