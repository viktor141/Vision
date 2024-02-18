package com.cifrazia.custommainmenu.core.ui.gui;

import com.cifrazia.custommainmenu.core.abstracts.Screen;
import com.cifrazia.custommainmenu.core.ui.buttons.IconButton;
import com.cifrazia.custommainmenu.core.ui.buttons.IconButton.Icon;
import com.cifrazia.custommainmenu.core.ui.buttons.base.Button;
import com.cifrazia.custommainmenu.menu.GeneralBusinessMenu;
import com.cifrazia.custommainmenu.menu.MainMenu;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class PauseButtons extends Screen {

    public PauseButtons(Minecraft mc) {
        this.mc = mc;

        addButton(new IconButton(this, 0, 0, Icon.MAP));
        addButton(new IconButton(this, 0, 0, Icon.QUEST));
        addButton(new IconButton(this, 0, 0, Icon.ISLAND));
        addButton(new IconButton(this, 0, 0, Icon.WAREHOUSE));
        addButton(new IconButton(this, 0, 0, Icon.SETTINGS));

        addButton(new IconButton(this, 0, 0, Icon.SHOP)).setEvent(() -> {mc.displayGuiScreen(new GeneralBusinessMenu());});
        addButton(new IconButton(this, 0, 0, Icon.CASES));
        addButton(new IconButton(this, 0, 0, Icon.DONATION));
        addButton(new IconButton(this, 0, 0, Icon.KITS));
        addButton(new IconButton(this, 0, 0, Icon.BATTLE_PASS));

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
        screenWidth = 1160 >> 1;
        screenHeight = 338 >> 1;

        screenStartX = (width - screenWidth) >> 1;
        screenStartY = (height - screenHeight) >> 1;

        screenEndX = screenStartX + screenWidth;
        screenEndY = screenStartY + screenHeight;


        int buttonStartX = screenEndX - ((buttonList.get(0).width + (22 >> 1)) * 4);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Button iconButton = (Button) buttonList.get((5 * i) + j);
                int x = buttonStartX + (j * (iconButton.width + (22 >> 1)));
                int y = screenStartY + (6 >> 1) + ((iconButton.height + (22 >> 1)) * i);
                iconButton.updateCords(x, y);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
