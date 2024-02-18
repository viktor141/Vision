package com.cifrazia.custommainmenu.core.ui.buttons.base;

import com.cifrazia.custommainmenu.CustomMainMenu;
import com.cifrazia.custommainmenu.core.abstracts.Gui;

public abstract class HighButton extends RectButton{

    public HighButton(Gui parentGUI, int x, int y, int color) {
        super(parentGUI, x, y, 138 >> 1, 152 >> 1, "", color);

        buttonTextureKit = CustomMainMenu.IN_GAME_KIT;

        texturePosX = 0;
        texturePosY = 222 >> 1;

        uUnHovered = 146 >> 1;
        uHovered = 310 >> 1;
        v = 214 >> 1;
        borderTextureWidth = 150 >> 1;
        borderTextureHeight = 164 >> 1;
    }

}
