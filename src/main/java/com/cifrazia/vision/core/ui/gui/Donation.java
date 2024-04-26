package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.ui.buttons.donations.*;
import com.cifrazia.vision.core.ui.util.panel.InformationPanel;
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


        int panelWidth = (int) (width * 0.28125);
        int x = screenStartX + (40 >> 1);
        int y = screenStartY + (128 >> 1);
        commands.setUp(x, y, panelWidth);
        possibilities.setUp(x + panelWidth + (18 >> 1), y, panelWidth);

        for (int i = 0; i < donationButtons.size(); i++) {
            DonationButton button = donationButtons.get(i);
            button.updateCords(screenStartX + (80 >> 1) + (i * (button.width + (10 >> 1))), screenStartY + (20 >> 1));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        commands.draw(mouseX, mouseY);
        possibilities.draw(mouseX, mouseY);
    }

    private void addDonationButton(DonationButton donationButton) {
        donationButtons.add(donationButton);
        addButton(donationButton);
    }
}
