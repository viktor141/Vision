package com.cifrazia.custommainmenu.core.ui.buttons;

import com.cifrazia.custommainmenu.core.abstracts.Gui;
import com.cifrazia.custommainmenu.core.ui.buttons.base.WideButton;

public class SmallButton extends WideButton {

    public SmallButton(Gui parentGUI, String buttonText) {
        super(parentGUI, 0, 0, 106 >> 1, 36 >> 1, buttonText, 14737632);

        texturePosX = 266 >> 1;
        texturePosY = 404 >> 1;

        borderSet();

        uUnHovered = 266 >> 1;
        uHovered = 382 >> 1;
        v = 448 >> 1;
        borderTextureWidth = 110 >> 1;
        borderTextureHeight = 40 >> 1;
    }

    @Override
    protected void borderSet() {
        borderX = x - 1;
        borderY = y - 1;
    }
}
