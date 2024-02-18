package com.cifrazia.custommainmenu.menu;


import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.gui.PauseButtons;
import net.minecraft.client.Minecraft;

public class InGameMenu extends Gui {

    private PauseButtons pauseButtons;

    @Override
    public void initGui() {
        super.initGui();

        pauseButtons = new PauseButtons(mc);
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        activeButtonScreen(mouseX, mouseY, partialTicks);
    }


    private void activeButtonScreen(int mouseX, int mouseY, float partialTicks) {

        pauseButtons.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        pauseButtons.setResolution(width, height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        pauseButtons.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
