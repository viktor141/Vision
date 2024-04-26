package com.cifrazia.vision.core.ui.gui;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.ui.buttons.IconButton;
import com.cifrazia.vision.core.ui.buttons.IconButton.Icon;
import com.cifrazia.vision.core.ui.buttons.base.Button;
import com.cifrazia.vision.menu.GeneralBusinessMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;

public class PauseButtons extends Screen {

    public PauseButtons(Minecraft mc, Gui parent) {
        this.mc = mc;

        addButton(new IconButton(this, 0, 0, Icon.MAP));
        addButton(new IconButton(this, 0, 0, Icon.QUEST));
        addButton(new IconButton(this, 0, 0, Icon.ISLAND));
        addButton(new IconButton(this, 0, 0, Icon.AUCTION));
        addButton(new IconButton(this, 0, 0, Icon.SETTINGS)).setEvent(() -> mc.displayGuiScreen(new GuiOptions(parent, mc.gameSettings)));

        addButton(new IconButton(this, 0, 0, Icon.SHOP))
                .setEvent(() -> mc.displayGuiScreen(new GeneralBusinessMenu("Shop")));

        addButton(new IconButton(this, 0, 0, Icon.CASES));
        addButton(new IconButton(this, 0, 0, Icon.DONATION))
                .setEvent(() -> mc.displayGuiScreen(new GeneralBusinessMenu("Donation")));

        addButton(new IconButton(this, 0, 0, Icon.BANK))
                .setEvent(() -> {
                    GeneralBusinessMenu menu = new GeneralBusinessMenu("Shop");
                    mc.displayGuiScreen(menu);
                    menu.shop.setBankScreen();
                });

        addButton(new IconButton(this, 0, 0, Icon.WAREHOUSE))
                .setEvent(() -> mc.displayGuiScreen(new GeneralBusinessMenu("Warehouse")));

    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        /*IconButton iconButton = (IconButton) buttonList.get(0);
        for (int i = 0; i < 5; i++) {
            int x = screenEndX - iconButton.width - (i * (iconButton.width + (22 >> 1)));
            int y = screenStartY + (6 >> 1);
            iconButton.updateCords(x, y);

            iconButton.drawButton(mc, mouseX, mouseY, partialTicks);
        }

        for (int i = 0; i < 5; i++) {
            int x = screenEndX - iconButton.width - (i * (iconButton.width + (22 >> 1)));
            int y = screenStartY + iconButton.height + (22 >> 1) + (6 >> 1);
            iconButton.updateCords(x, y);

            iconButton.drawButton(mc, mouseX, mouseY, partialTicks);
        }*/

    }


    public void setResolution(int width, int height) {
        screenStartX = 60 >> 1;
        screenStartY = 190 >> 1;

        screenEndX = width - (60 >> 1);
        screenEndY = height - (192 >> 1);

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        int buttonStartX = screenEndX - (((150 + 10) >> 1) * 5);
        int buttonStartY = (screenHeight >> 1) + screenStartY - ((164 + 5) >> 1);
        System.out.println(buttonStartX + " ");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Button iconButton = (Button) buttonList.get((5 * i) + j);
                int x = buttonStartX + (j * (iconButton.width + (22 >> 1)));
                int y = buttonStartY + (6 >> 1) + ((iconButton.height + (22 >> 1)) * i);
                iconButton.updateCords(x, y);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
