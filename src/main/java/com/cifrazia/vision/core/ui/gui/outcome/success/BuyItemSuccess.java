package com.cifrazia.vision.core.ui.gui.outcome.success;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.PanelScreen;
import com.cifrazia.vision.core.ui.buttons.modal.ModalBlueButton;
import com.cifrazia.vision.core.ui.buttons.modal.ModalGrayButton;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.panel.ConfirmationPanel;
import com.cifrazia.vision.menu.GeneralBusinessMenu;
import net.minecraft.client.Minecraft;

public class BuyItemSuccess extends PanelScreen {

    private final ModalGrayButton close;
    private final ModalBlueButton toWarehouse;
    private final String itemName;

    public BuyItemSuccess(Minecraft mc, Gui parentGui, String itemName) {
        super(new ConfirmationPanel(mc));
        this.mc = mc;
        this.parentGui = parentGui;
        this.itemName = itemName;

        close = addButton(new ModalGrayButton(this, 0, 0, "Close", -1));
        toWarehouse = addButton(new ModalBlueButton(this, 0, 0, "To Warehouse", -1));

        close.setEvent(()-> {
            mc.displayGuiScreen(parentGui);
        });

        toWarehouse.setEvent(()-> {
            mc.displayGuiScreen(new GeneralBusinessMenu("Warehouse"));
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(mc.fontRenderer, "You have successfully acquired", screenStartX + (screenWidth >> 1), screenStartY + (34 >> 1), Color.WHITE_100.getFullColor());
        drawCenteredString(mc.fontRenderer, itemName, screenStartX + (screenWidth >> 1), screenStartY + (60 >> 1), Color.GREEN.getFullColor());
        drawCenteredString(mc.fontRenderer, "THE ITEM HAS BEEN SHIPPED TO THE WAREHOUSE!", screenStartX + (screenWidth >> 1), screenStartY + (96 >> 1), Color.BLUE_MODAL.getFullColor());
    }

    @Override
    public void setResolution(int width, int height) {
        screenWidth = 520 >> 1;
        screenHeight = 166 >> 1;

        super.setResolution(width, height);

        close.updateCords(screenStartX + (54 >> 1), screenStartY + (130 >> 1));
        toWarehouse.updateCords(screenStartX + (264 >> 1), screenStartY + (130 >> 1));
    }
}
