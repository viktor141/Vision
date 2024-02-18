package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.StaticButton;

public class BuyItemsButton extends StaticButton {

    public BuyItemsButton(Gui parentGUI, int x, int y, String buttonText, int color) {
        super(parentGUI, x, y, 164 >> 1, 38 >> 1, buttonText, color);

        buttonTextureKit = CustomMainMenu.NAVIGATION_SHOP_KIT;

        texturePosX = 0;
        texturePosY = 264 >> 1;

        uHovered = 172 >> 1;
        v = 260 >> 1;
        borderTextureWidth = 168 >> 1;
        borderTextureHeight = 42 >> 1;
    }

    @Override
    protected void borderSet() {
        borderX = x - 1;
        borderY = y - 1;
    }
}
