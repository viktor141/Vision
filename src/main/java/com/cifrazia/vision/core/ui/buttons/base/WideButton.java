package com.cifrazia.vision.core.ui.buttons.base;

import com.cifrazia.vision.core.abstracts.Gui;

public abstract class WideButton extends RectButton {

    public WideButton(Gui parentGUI, int x, int y, String buttonText) {
        this(parentGUI, x, y, 198 >> 1, 54 >> 1, buttonText, 14737632);
    }

    protected WideButton(Gui parentGUI, int x, int y, int width, int height, String buttonText, int color) {
        super(parentGUI, x, y, width, height, buttonText, color);

        uUnHovered = 62 >> 1;
        uHovered = 282 >> 1;
        v = 276 >> 1;
        borderTextureWidth = 210 >> 1;
        borderTextureHeight = 66 >> 1;
    }

}
