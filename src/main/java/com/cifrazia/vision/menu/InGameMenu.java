package com.cifrazia.vision.menu;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.RedWideButton;
import com.cifrazia.vision.core.ui.buttons.StatisticButton;
import com.cifrazia.vision.core.ui.buttons.links.DiscordLink;
import com.cifrazia.vision.core.ui.buttons.links.VkLink;
import com.cifrazia.vision.core.ui.buttons.links.YouTubeLink;
import com.cifrazia.vision.core.ui.gui.BankPanel;
import com.cifrazia.vision.core.ui.gui.PauseButtons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.realms.RealmsBridge;

public class InGameMenu extends Gui {

    private PauseButtons pauseButtons;
    private BankPanel bankPanel;
    private int lowerLine;

    @Override
    public void initGui() {
        super.initGui();

        pauseButtons = new PauseButtons(mc, this);
        bankPanel = new BankPanel(mc, false);

        lowerLine = height - (86 >> 1);

        addButton(new RedWideButton(this, width - (224 >> 1), lowerLine, "Exit").setEvent(() -> {
            this.mc.world.sendQuittingDisconnectingPacket();
            this.mc.loadWorld(null);

            this.mc.displayGuiScreen(new MainMenu());
        }));

        addButton(new VkLink(this, width - (538 >> 1), lowerLine));
        addButton(new DiscordLink(this, width - (462 >> 1), lowerLine));
        addButton(new YouTubeLink(this, width - (386 >> 1), lowerLine));

        addButton(new StatisticButton(this, width - (310 >> 1), lowerLine).setEvent(() -> mc.displayGuiScreen(new GuiStats(this, this.mc.player.getStatFileWriter()))));
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        pauseButtons.drawScreen(mouseX, mouseY, partialTicks);
        bankPanel.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        super.setWorldAndResolution(mc, width, height);

        pauseButtons.setResolution(width, height);
        bankPanel.setUp(22 >> 1, 22 >> 1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        pauseButtons.mouseClicked(mouseX, mouseY, mouseButton);
        bankPanel.mouseClicked(mouseX, mouseY, mouseButton);
    }
}