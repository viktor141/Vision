package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveButton;
import com.cifrazia.custommainmenu.core.ui.buttons.base.ActiveTransparentButton;

import java.util.List;

public class CategoryButton extends ActiveTransparentButton {

    public CategoryButton(Gui parentGUI, List<? extends ActiveButton> buttons, int x, int y, String buttonText) {
        super(parentGUI, buttons, x, y, 182 >> 1, 46 >> 1, buttonText);

        buttonTextureKit = CustomMainMenu.NAVIGATION_SHOP_KIT;

        texturePosX = 54 >> 1;
        texturePosY = 458 >> 1;
    }
}
