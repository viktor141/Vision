package com.cifrazia.custommainmenu.core.ui.gui;

import com.cifrazia.custommainmenu.core.abstracts.Screen;
import com.cifrazia.custommainmenu.core.ui.buttons.donations.*;
import com.cifrazia.custommainmenu.core.ui.util.panel.InformationPanel;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Donation extends Screen {

    private final List<DonationButton> donationButtons = new ArrayList<>();
    private final InformationPanel commands;
    private final InformationPanel possibilities;

    public Donation(Minecraft mc, int screenStartX, int screenStartY) {
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;

        commands = new InformationPanel(mc);
        possibilities = new InformationPanel(mc);

        addDonationButton(new MasterButton(this, donationButtons, 0, 0));
        addDonationButton(new HeroButton(this, donationButtons, 0, 0));
        addDonationButton(new LegendButton(this, donationButtons, 0, 0));
        addDonationButton(new ImmortalButton(this, donationButtons, 0, 0));
    }

    @Override
    public void setResolution(int width, int height) {
        screenEndX = width;
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        float scalePanel = 1.4f;

        //commands.setScale(scalePanel);
        //possibilities.setScale(scalePanel);

        int x = screenStartX + (40 >> 1), y = screenStartY + (128 >> 1);
        int gap = (int) ((18 >> 1) * scalePanel);
        commands.setUp(x, y);
        possibilities.setUp(x + commands.getWidth() + gap, y);

        float scaleButton = 1f;//1.22f;//1.2258f

        for (int i = 0; i < donationButtons.size(); i++) {
            DonationButton button = donationButtons.get(i);
            button.setScale(scaleButton);
            int interval = (int) ((50 >> 1) * scaleButton);
            button.updateCords(screenStartX + (80 >> 1) + (i * (button.width + interval)), screenStartY + (20 >> 1));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        commands.draw(mouseX,mouseY);
        //possibilitiesPanel.drawPanel(mouseX, mouseY);
    }

    private void addDonationButton(DonationButton donationButton) {
        donationButtons.add(donationButton);
        addButton(donationButton);
    }
}
