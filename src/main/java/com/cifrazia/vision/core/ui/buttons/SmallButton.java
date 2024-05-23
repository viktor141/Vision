package com.cifrazia.vision.core.ui.buttons;

import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.ui.buttons.base.WideButton;

public class SmallButton extends WideButton {

    public SmallButton(Gui parentGUI, String buttonText) {
        super(parentGUI, 0, 0, 106 >> 1, 36 >> 1, buttonText, 14737632);

        texturePosX = 266 >> 1;
        texturePosY = 404 >> 1;

        borderSet();

        uUnHovered = 264 >> 1;
        uHovered = 380 >> 1;
        v = 446 >> 1;
        borderTextureWidth = 114 >> 1;
        borderTextureHeight = 44 >> 1;
    }

    @Override
    protected void borderSet() {
        borderX = x - 2;
        borderY = y - 2;
    }
}
