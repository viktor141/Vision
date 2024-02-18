package com.cifrazia.custommainmenu.menu;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.*;
import com.cifrazia.custommainmenu.core.ui.buttons.links.DiscordLink;
import com.cifrazia.custommainmenu.core.ui.buttons.links.VkLink;
import com.cifrazia.custommainmenu.core.ui.gui.ServerList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class MainMenu extends Gui {

    private int lowerLine;
    private ServerList serverList;

    @Override
    public void initGui() {
        super.initGui();

        serverList = new ServerList(mc);

        lowerLine = height - (86 >> 1);



        addButton(new GrayWideButton(this,  20 >> 1, lowerLine, "Settings"));
        addButton(new RedWideButton(this,  width - (224 >> 1), lowerLine, "Exit"));


        addButton(new VkLink(this, width - (376 >> 1), lowerLine));
        addButton(new DiscordLink(this,  width - (300 >> 1), lowerLine));

        addButton(new StatisticButton(this,  244 >> 1, lowerLine));
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        //drawRect(0,0, width, height, (255 * 256^3) + (2 * 256^2) + (255 * 256) + 255);

        //drawGradientRect(0, 0, this.width, this.height, (255 * 256^3) + (2 * 256^2) + (255 * 256) + 255, (255 * 256^3) + (255 * 256^2) + (255 * 256) + 255);

        drawDefaultBackground();

        //drawRect(0, 0, width, height, -923404811);
        //drawString(fontRenderer, "Huila", 50, 50, (50 * 256^2) + (250 * 256) + 250);
        serverList.drawServerListGui(mouseX, mouseY, partialTicks);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        serverList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);


    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        serverList.setResolution(width, height);
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        super.onResize(mcIn, w, h);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        serverList.handleMouseInput();
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        serverList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        serverList.mouseReleased(mouseX, mouseY, state);
    }
}
