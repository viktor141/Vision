package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveTransparentButton;

import java.util.List;

public class CatalogButton extends ActiveTransparentButton {

    public CatalogButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, String buttonText) {
        super(parentGUI, buttons, x, y, 164 >> 1, 36 >> 1, buttonText);

        buttonTextureKit = CustomMainMenu.NAVIGATION_SHOP_KIT;

        texturePosX = 0;
        texturePosY = 418 >> 1;
    }

}
